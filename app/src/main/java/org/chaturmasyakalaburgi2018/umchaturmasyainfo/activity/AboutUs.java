package org.chaturmasyakalaburgi2018.umchaturmasyainfo.activity;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;

import org.chaturmasyakalaburgi2018.umchaturmasyainfo.R;
import org.chaturmasyakalaburgi2018.umchaturmasyainfo.model.UMCEvents;
import org.chaturmasyakalaburgi2018.umchaturmasyainfo.model.UMUrls;
import org.chaturmasyakalaburgi2018.umchaturmasyainfo.model.Upload;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AboutUs extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;
    String website = null;
    String websiteName = null;
    private Button mButtonChooseImage;
    private Button mButtonUpload,urlUpdate;
    private TextView mTextViewShowUploads;
    private EditText mEditTextFileName,date,discreption, subtitle,urlid,urdName,urlUrl,urldate;
    private EditText eventId,eventTitle,eventSubTitle,eventDiscription,eventDate;
    private Button eventUpdate;
    private ImageView mImageView;
    private ProgressBar mProgressBar;

    private Uri mImageUri;

    private StorageReference mStorageRef;
    private DatabaseReference mDatabaseRef;

    private StorageTask mUploadTask;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference rootdatabaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        mButtonChooseImage = findViewById(R.id.button_choose_image);
        mButtonUpload = findViewById(R.id.button_upload);
        mTextViewShowUploads = findViewById(R.id.text_view_show_uploads);
        mEditTextFileName = findViewById(R.id.name);
        date = findViewById(R.id.date);
        discreption = findViewById(R.id.discreption);
        subtitle = findViewById(R.id.editText_subtitle);
        urlid = findViewById(R.id.urlid);
        urdName = findViewById(R.id.urlName);
        urlUrl = findViewById(R.id.urlUrl);
        urlUpdate = findViewById(R.id.urlUpdate);
        urldate = findViewById(R.id.urldate);

        eventId = findViewById(R.id.eventid);
        eventTitle = findViewById(R.id.eventTitle);
        eventSubTitle = findViewById(R.id.eventSubTitle);
        eventDiscription = findViewById(R.id.eventDiscription);
        eventDate = findViewById(R.id.eventDate);
        eventUpdate = findViewById(R.id.eventUpdate);

        firebaseDatabase = FirebaseDatabase.getInstance();
//        firebaseDatabase.setPersistenceEnabled(true);
        rootdatabaseReference = firebaseDatabase.getReference();



        eventUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UMCEvents events = new UMCEvents();
                events.setEventId(Integer.parseInt(eventId.getText().toString()));
                events.setEventDate(eventDate.getText().toString());
                events.setEventTitle(eventTitle.getText().toString());
                events.setEventSubTiltle(eventSubTitle.getText().toString());
                events.setEventDiscription(eventDiscription.getText().toString());
                rootdatabaseReference.child("UMCEvents").child(String.valueOf(events.getEventId())).setValue(events);
            }
        });
        date.setText(new SimpleDateFormat("dd-MM-yyyy").format(new Date()));
        mEditTextFileName.setText("Sri MoolaRamo Vijayate");



        rootdatabaseReference.child("UMUrls").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int count = 4;
                website = dataSnapshot.child(String.valueOf(count)).child("urlUrl").getValue(String.class);
                websiteName = dataSnapshot.child(String.valueOf(count)).child("urlName").getValue(String.class);
                Log.e("TAG url",""+ website+websiteName);

                urlid.setText(""+count);
                urdName.setText(websiteName);
                urlUrl.setText(website);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        });
        urlUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (urldate.getText().toString().equals("")) {
                    UMUrls urls = new UMUrls();
                    urls.setUrlId(Integer.parseInt(urlid.getText().toString()));
                    urls.setUrlName(urdName.getText().toString());
                    urls.setUrlUrl(urlUrl.getText().toString());
                    rootdatabaseReference.child("UMUrls").child(String.valueOf(urls.getUrlId())).setValue(urls);
                    Toast.makeText(AboutUs.this, "Updated successfuly", Toast.LENGTH_LONG).show();
                }else {
                    UMUrls urls = new UMUrls();
                    urls.setUrlId((int) System.currentTimeMillis());
                    urls.setUrldate(urldate.getText().toString());
                    urls.setUrlName(urdName.getText().toString());
                    urls.setUrlUrl(urlUrl.getText().toString());
                    rootdatabaseReference.child("UMUrls").child("3").child("UMUrls").child(String.valueOf(urls.getUrlId())).setValue(urls);
                    Toast.makeText(AboutUs.this, "Updated successfuly", Toast.LENGTH_LONG).show();

                }
            }
        });

        mImageView = findViewById(R.id.image_view);
        mProgressBar = findViewById(R.id.progress_bar);

        mStorageRef = FirebaseStorage.getInstance().getReference("Updates");
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("Updates");

        mButtonChooseImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFileChooser();
            }
        });

        mButtonUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mUploadTask != null && mUploadTask.isInProgress()) {
                    Toast.makeText(AboutUs.this, "Upload in progress", Toast.LENGTH_SHORT).show();
                } else {
                    uploadFile();
                }
            }
        });

/*        mTextViewShowUploads.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openImagesActivity();
            }
        });*/
    }

    private void openFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null) {
            mImageUri = data.getData();

            Glide.with(this)
                    .load(mImageUri)
                    .crossFade()
                    .thumbnail(0.5f)
                    .into(mImageView);
//            Picasso.with(this).load(mImageUri).into(mImageView);
        }
    }

    private String getFileExtension(Uri uri) {
        ContentResolver cR = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }

    private void uploadFile() {
        if (mImageUri != null) {
            final StorageReference fileReference = mStorageRef.child(System.currentTimeMillis()
                    + "." + getFileExtension(mImageUri));
            fileReference.putFile(mImageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    fileReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            Log.e( "onSuccess: uri= ", uri.toString());
                            Upload upload = new Upload();
                            upload.setName(mEditTextFileName.getText().toString().trim());
                            upload.setImageUrl(String.valueOf(uri.toString()));
                            upload.setSubTitle(subtitle.getText().toString().trim());
                            upload.setDiscreption(discreption.getText().toString().trim());
                            upload.setDate(date.getText().toString().trim());
                            String uploadId = mDatabaseRef.push().getKey();
                            mDatabaseRef.child(uploadId).setValue(upload);
                            Toast.makeText(AboutUs.this, "Upload successful", Toast.LENGTH_LONG).show();
                        }
                    });
                }
            });
        } else {
            Toast.makeText(this, "No file selected", Toast.LENGTH_SHORT).show();
        }
    }
}
