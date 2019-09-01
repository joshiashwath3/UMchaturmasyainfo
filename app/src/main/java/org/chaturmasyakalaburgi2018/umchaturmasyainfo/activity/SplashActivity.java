package org.chaturmasyakalaburgi2018.umchaturmasyainfo.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;
import android.util.Log;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.chaturmasyakalaburgi2018.umchaturmasyainfo.R;
import org.chaturmasyakalaburgi2018.umchaturmasyainfo.common.FolderCreation;


public class SplashActivity extends Activity {
    private static int SPLASH_TIME_OUT = 3000;

    public static String website = null;
    public static String satyatmvani = null;
    public static String videospravachana = null;
    public static String livepravachan = null;
    public static String nearbyplaceses = null;
    public static String voluntaryservices = null;
    public static String facebook = null;

    public static int count;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference rootdatabaseReference;// this root database name

    FolderCreation folderCreation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        count++;

        folderCreation = new FolderCreation();
        folderCreation.folder();
        if (MainActivity.count != 0){
            count = MainActivity.count;
        }

        firebaseDatabase = FirebaseDatabase.getInstance();
//        firebaseDatabase.setPersistenceEnabled(true);
        rootdatabaseReference = firebaseDatabase.getReference();

        rootdatabaseReference.child("UMUrls").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int count = 1;
                website = dataSnapshot.child(String.valueOf(count)).child("urlUrl").getValue(String.class);
                Log.e("TAG url",""+ website);
                count++;
                satyatmvani = dataSnapshot.child(String.valueOf(count)).child("urlUrl").getValue(String.class);
                Log.e("TAG url",""+ satyatmvani);
                count++;
                videospravachana = dataSnapshot.child(String.valueOf(count)).child("urlUrl").getValue(String.class);
                Log.e("TAG url",""+ videospravachana);
                count++;
                livepravachan = dataSnapshot.child(String.valueOf(count)).child("urlUrl").getValue(String.class);
                Log.e("TAG url",""+ livepravachan);
                count++;
                nearbyplaceses = dataSnapshot.child(String.valueOf(count)).child("urlUrl").getValue(String.class);
                Log.e("TAG url",""+ nearbyplaceses);
                count++;
                voluntaryservices = dataSnapshot.child(String.valueOf(count)).child("urlUrl").getValue(String.class);
                Log.e("TAG url",""+ voluntaryservices);
                count++;
                facebook = dataSnapshot.child(String.valueOf(count)).child("urlUrl").getValue(String.class);
                Log.e("TAG url",""+ facebook);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        });

/*        Help help = new Help();
        help.setHelpID(1);
        help.setHelpName("Committee Members\n" +
                "\nPdt. Gopalachar Akamanchi");
        help.setHelpContact("9449618707");
        rootdatabaseReference.child("Help").child(String.valueOf(help.getHelpID())).setValue(help);

        help.setHelpID(2);
        help.setHelpName("Pdt. Venkannachar Pujar");
        help.setHelpContact("9448181288");
        rootdatabaseReference.child("Help").child(String.valueOf(help.getHelpID())).setValue(help);

        help.setHelpID(3);
        help.setHelpName("Pdt. Vinodachar Galagali");
        help.setHelpContact("9448441740");
        rootdatabaseReference.child("Help").child(String.valueOf(help.getHelpID())).setValue(help);

        help.setHelpID(4);
        help.setHelpName("Sri. Ramachar Ghanti\t");
        help.setHelpContact("9686528491");
        rootdatabaseReference.child("Help").child(String.valueOf(help.getHelpID())).setValue(help);

        help.setHelpID(5);
        help.setHelpName("Enquiry Committee\n" +
                "\nSri. Radhakrishna Kulkarni");
        help.setHelpContact("9880801550");
        rootdatabaseReference.child("Help").child(String.valueOf(help.getHelpID())).setValue(help);

        help.setHelpID(6);
        help.setHelpName("Sri. Madhusudhan Bilurkar");
        help.setHelpContact("9449619070");
        rootdatabaseReference.child("Help").child(String.valueOf(help.getHelpID())).setValue(help);


        help.setHelpID(7);
        help.setHelpName("Accomadation Committee\n" +
                "\nSri. Jayateerth Bagewadi  ");
        help.setHelpContact("9845676623");
        rootdatabaseReference.child("Help").child(String.valueOf(help.getHelpID())).setValue(help);

        help.setHelpID(8);
        help.setHelpName("Sri. Deepak Savalgi");
        help.setHelpContact("8123124632");
        rootdatabaseReference.child("Help").child(String.valueOf(help.getHelpID())).setValue(help);

        help.setHelpID(9);
        help.setHelpName("Transport Committee\n" +
                "\nSri. Raghavendra Nillur");
        help.setHelpContact("9482060600");
        rootdatabaseReference.child("Help").child(String.valueOf(help.getHelpID())).setValue(help);

        help.setHelpID(10);
        help.setHelpName("Sri. Aravind Kulkarni\t");
        help.setHelpContact("8951634056");
        rootdatabaseReference.child("Help").child(String.valueOf(help.getHelpID())).setValue(help);

        help.setHelpID(11);
        help.setHelpName("Padapooja Committee\n" +
                "\nSri. Narayan Omkar");
        help.setHelpContact("9448160015");
        rootdatabaseReference.child("Help").child(String.valueOf(help.getHelpID())).setValue(help);

        help.setHelpID(12);
        help.setHelpName("Sri. Venkatesh Ashtagi");
        help.setHelpContact("9538565696");
        rootdatabaseReference.child("Help").child(String.valueOf(help.getHelpID())).setValue(help);*/



/*        Upload urls = new Upload();
        urls.setName("Hare srinivas");
        urls.setImageUrl("https://en.wikipedia.org/wiki/Satyatma_Tirtha#/media/File:Shree_Pic_2014-04-03_01-41.jpg");
        urls.setSubTitle("website");
        urls.setDate(new SimpleDateFormat("dd-MM-yyyy").format(new Date()));
        urls.setDiscreption("UM Chaturmasya Info is used giving information about Chaturmasya vrata");
        rootdatabaseReference.child("Updates").child(String.valueOf(System.currentTimeMillis())).setValue(urls);*/

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }, SPLASH_TIME_OUT);
    }

}

