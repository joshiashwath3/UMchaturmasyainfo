package org.chaturmasyakalaburgi2018.umchaturmasyainfo.activity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import androidx.core.view.ViewPager;
import androidx.appcompat.app.AlertDialog;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import androidx.core.view.GravityCompat;
import androidx.core.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.android.gms.location.LocationListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.chaturmasyakalaburgi2018.umchaturmasyainfo.R;
import org.chaturmasyakalaburgi2018.umchaturmasyainfo.adapter.SlideTaabViewPagerAdapter;
import org.chaturmasyakalaburgi2018.umchaturmasyainfo.common.CircleTransform;
import org.chaturmasyakalaburgi2018.umchaturmasyainfo.common.CommonLocation;
import org.chaturmasyakalaburgi2018.umchaturmasyainfo.common.FolderCreation;
import org.chaturmasyakalaburgi2018.umchaturmasyainfo.common.SessionManagement;
import org.chaturmasyakalaburgi2018.umchaturmasyainfo.controller.LivePravachana;
import org.chaturmasyakalaburgi2018.umchaturmasyainfo.controller.VideoPravachana;
import org.chaturmasyakalaburgi2018.umchaturmasyainfo.controller.howToReachUM.HowToReachUMK;
import org.chaturmasyakalaburgi2018.umchaturmasyainfo.controller.seva.SevaBooking;
import org.chaturmasyakalaburgi2018.umchaturmasyainfo.model.UMCUser;
import org.chaturmasyakalaburgi2018.umchaturmasyainfo.util.CameraPermissionUtil;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,LocationListener {

    private ViewPager viewPager;
    private TabLayout tabLayout;
    private String[] pageTitle = {"Events","View","Updates"};
    private NavigationView navigationView;
    private DrawerLayout drawer;

    /*****************************************************/
    /** Camera Starts here
     /*****************************************************/

    String filePath = "/Android/data/org.chaturmasyakalaburgi2018/images/imgpbk/";
    private int REQUEST_CAMERA = 0, SELECT_FILE = 1;
    private String userChoosenTask;

    private View navHeader;

    ImageView imageHeader;
    TextView nameHeader;
    TextView emailHeader;
    SessionManagement session;
    String loginName = null;
    String loginContact = null;
    String loginEmail = null;

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference rootdatabaseReference;

    public static int count;
    public static double currentLatitude;
    public static double currentLongitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        count =2;

        firebaseDatabase = FirebaseDatabase.getInstance();
        rootdatabaseReference = firebaseDatabase.getReference();
        // Session Manager
        session = new SessionManagement(this);



        viewPager = (ViewPager)findViewById(R.id.view_pager);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.nav_view);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,SevaBooking.class));
            }
        });

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        for (int i = 0; i < 3; i++) {
            tabLayout.addTab(tabLayout.newTab().setText(pageTitle[i]));
        }

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        assert navigationView != null;
        navigationView.setNavigationItemSelectedListener(this);

        SlideTaabViewPagerAdapter pagerAdapter = new SlideTaabViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);

        viewPager.setCurrentItem(1);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });


        HashMap<String, String> loginUser = session.getUserDetails();
        loginName = loginUser.get(SessionManagement.KEY_NAME);
        loginContact = loginUser.get(SessionManagement.KEY_MOBILE_N0);
        loginEmail = loginUser.get(SessionManagement.KEY_Email);
        navHeader = navigationView.getHeaderView(0);
        nameHeader =(TextView)navHeader.findViewById(R.id.textViewHeaderName);
        if (loginName == null){
            nameHeader.setText(R.string.nav_header_title);
        }else{
            nameHeader.setText(loginName);
        }
        nameHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (loginName == null){
                    editUser();
                }else{
                    updateUser();
                }
            }
        });

        emailHeader = (TextView)navHeader.findViewById(R.id.textViewHeaderEmail);

        if (loginName == null){
            emailHeader.setText(R.string.nav_header_subtitle);
        }else{
            emailHeader.setText(loginEmail);
        }
        emailHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (loginEmail == null){
                    editUser();
                }else{
                    updateUser();
                }
            }
        });

        imageHeader = (ImageView)navHeader.findViewById(R.id.imageViewHeader);
        imageHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (loginName == null){
                    editUser();
                }else{
                    selectImage();
                }
            }
        });

        init();
    }
    private void editUser(){

        View alertLayout = LayoutInflater.from(MainActivity.this)
                .inflate(R.layout.add_user,null);
        final EditText editTextFirstName = alertLayout.findViewById(R.id.user_other_feedback);
        final EditText editTextContactOne = alertLayout.findViewById(R.id.user_feedback);
        final EditText editTextEmail = alertLayout.findViewById(R.id.user_email);

        new AlertDialog.Builder(MainActivity.this).setView(alertLayout)
                .setView(alertLayout)
                .setCancelable(false)
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        Toast.makeText(MainActivity.this, "Cancel clicked", Toast.LENGTH_SHORT).show();
                    }
                })
                .setPositiveButton("Save", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(final DialogInterface dialog, int which) {
                        String  stringFirstName= editTextFirstName.getText().toString();
                        String stringContactOne = editTextContactOne.getText().toString();
                        String stringEmail = editTextEmail.getText().toString();


                            UMCUser user = new UMCUser();
                            user.setUserFullName(stringFirstName);
                            user.setUserContact(stringContactOne);
                            user.setUserEmail(stringEmail);

                            rootdatabaseReference.child("UMCUser").child(String.valueOf(user.getUserContact())).setValue(user);
                            Toast.makeText(MainActivity.this,"User Updated successfully",Toast.LENGTH_SHORT).show();
                            finish();
                            startActivity(getIntent());

                    }
                }).create().show();

    }

    private void updateUser(){

        View alertLayout = LayoutInflater.from(MainActivity.this)
                .inflate(R.layout.add_user,null);
        final TextView textViewTitle = alertLayout.findViewById(R.id.textViewTitle);
        final EditText editTextFirstName = alertLayout.findViewById(R.id.user_other_feedback);
        final EditText editTextContactOne = alertLayout.findViewById(R.id.user_feedback);
        final EditText editTextEmail = alertLayout.findViewById(R.id.user_email);

        textViewTitle.setText("Update User");
        editTextFirstName.setText(loginName);
        editTextContactOne.setText(loginContact);
        editTextEmail.setText(loginEmail);
        new AlertDialog.Builder(MainActivity.this).setView(alertLayout)
                .setView(alertLayout)
                .setCancelable(false)
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        Toast.makeText(MainActivity.this, "Cancel clicked", Toast.LENGTH_SHORT).show();
                    }
                })
                .setPositiveButton("Save", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(final DialogInterface dialog, int which) {
                        String  stringFirstName= editTextFirstName.getText().toString();
                        String stringContactOne = editTextContactOne.getText().toString();
                        String stringEmail = editTextEmail.getText().toString();

                        session.createLoginSession(stringFirstName, stringContactOne,stringEmail);

                        UMCUser user = new UMCUser();
                        user.setUserFullName(stringFirstName);
                        user.setUserContact(stringContactOne);
                        user.setUserEmail(stringEmail);

                        rootdatabaseReference.child("UMCUser").child(String.valueOf(user.getUserContact())).setValue(user);
                        Toast.makeText(MainActivity.this,"User Updated successfully",Toast.LENGTH_SHORT).show();
                        finish();
                        startActivity(getIntent());

                    }
                }).create().show();
    }

    private void init() {
        imageHeader.setImageResource(R.mipmap.ic_launcher_round);

        if (loginName == null) {
        } else {
            File destination = new File("/storage/emulated/0/Android/data/org.chaturmasyakalaburgi2018/images/imgpbk/" + loginName + ".jpg");
            if (destination.exists()) {
                Glide.with(this)
                        .load(destination)
                        .crossFade()
                        .thumbnail(0.5f)
                        .bitmapTransform(new CircleTransform(this))
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .skipMemoryCache(true)
                        .into(imageHeader);
            }
        }
    }

    boolean doubleBackToExitPressedOnce = false;
    @Override
    public void onBackPressed() {

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }

        if (doubleBackToExitPressedOnce) {

            Intent start = new Intent(Intent.ACTION_MAIN);
            start.addCategory(Intent.CATEGORY_HOME);
            start.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            start.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(start);
            System.exit(0);
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 3000);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            finish();
        } else {
            if (id == R.id.action_refresh) {
                finish();
                startActivity(getIntent());
            } else {
                if (id == R.id.action_exit) {
                    Intent start = new Intent(Intent.ACTION_MAIN);
                    start.addCategory(Intent.CATEGORY_HOME);
                    start.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    start.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(start);
                    System.exit(0);
                    return true;
                } else {
                    if (id == R.id.action_about) {
                        startActivity(new Intent(MainActivity.this, AboutUs.class));
                        return true;
                    }
                }
            }

        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_PadaPoojaBooking) {
            viewPager.setCurrentItem(2);

        } else if (id == R.id.nav_SavaList) {
            startActivity(new Intent(MainActivity.this,SevaBooking.class));

        } else if (id == R.id.nav_Event) {
            viewPager.setCurrentItem(0);

        } else if (id == R.id.nav_audio_pravchana) {
            startActivity(new Intent(MainActivity.this,CommonWebView.class).putExtra("url",SplashActivity.satyatmvani));

        } else if (id == R.id.nav_video_pravchana) {
            startActivity(new Intent(MainActivity.this,VideoPravachana.class).putExtra("url",SplashActivity.videospravachana));

        } else if (id == R.id.nav_live_pravchana) {
            startActivity(new Intent(MainActivity.this,LivePravachana.class).putExtra("url",SplashActivity.livepravachan));

        } else if (id == R.id.nav_nearByPlace) {
            startActivity(new Intent(MainActivity.this,CommonWebView.class).putExtra("url",SplashActivity.nearbyplaceses));

        } else if (id == R.id.nav_howtoreachMath) {
            startActivity(new Intent(MainActivity.this,HowToReachUMK.class));

        } else if (id == R.id.nav_vounteering_reistratin) {
            startActivity(new Intent(MainActivity.this,CommonWebView.class).putExtra("url",SplashActivity.voluntaryservices));

        }else if (id == R.id.nav_facebook) {
            String uri = SplashActivity.facebook;
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
            startActivity(intent);

        } else if (id == R.id.nav_helpLine) {
            startActivity(new Intent(MainActivity.this,HelpLine.class));

        } else if (id == R.id.nav_feedback) {
            startActivity(new Intent(MainActivity.this,FeedbackActivity.class));

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /***
     *  Profile image set
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case CameraPermissionUtil.MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if(userChoosenTask.equals("Take Photo"))
                        cameraIntent();
                    else if(userChoosenTask.equals("Choose from Library"))
                        galleryIntent();
                } else {
                    //code for deny
                }
                break;
        }
    }

    private void selectImage() {
        final CharSequence[] items = { "Take Photo", "Choose from Library",
                "Cancel" };

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Add Photo!");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                boolean result=CameraPermissionUtil.checkPermission(MainActivity.this);

                if (items[item].equals("Take Photo")) {
                    userChoosenTask ="Take Photo";
                    if(result)
                        cameraIntent();

                } else if (items[item].equals("Choose from Library")) {
                    userChoosenTask ="Choose from Library";
                    if(result)
                        galleryIntent();

                } else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    private void galleryIntent()
    {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select File"),SELECT_FILE);
    }

    private void cameraIntent()
    {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, REQUEST_CAMERA);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == SELECT_FILE)
                onSelectFromGalleryResult(data);
            else if (requestCode == REQUEST_CAMERA)
                onCaptureImageResult(data);
        }
    }

    private void onCaptureImageResult(Intent data) {

        Bitmap bm = (Bitmap) data.getExtras().get("data");
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG, 90, bytes);

        File destination = new File(Environment.getExternalStorageDirectory(), filePath +loginName + ".jpg");
        FileOutputStream fo;
        try {
            destination.createNewFile();
            fo = new FileOutputStream(destination);
            fo.write(bytes.toByteArray());
            fo.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Glide.with(this)
                .load(bytes.toByteArray())
                .asBitmap()
                .crossFade()
                .thumbnail(0.5f)
                .transform(new CircleTransform(this))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageHeader);
    }

    @SuppressWarnings("deprecation")
    private void onSelectFromGalleryResult(Intent data) {

        Bitmap bm=null;
        if (data != null) {
            try {
                bm = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(), data.getData());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        File destination = new File(Environment.getExternalStorageDirectory(), filePath +loginName + ".jpg");
        FileOutputStream fo;
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(destination);
            bm.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        imageHeader.setImageBitmap(bm);
        finish();
        startActivity(getIntent());
    }

    @Override
    public void onLocationChanged(Location location) {
            currentLatitude = location.getLatitude();
            currentLongitude = location.getLongitude();
        Log.e("Location Lat Long", String.valueOf(currentLatitude+currentLongitude));

    }
}
