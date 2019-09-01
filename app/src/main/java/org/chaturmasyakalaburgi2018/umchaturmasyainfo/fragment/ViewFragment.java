package org.chaturmasyakalaburgi2018.umchaturmasyainfo.fragment;


import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import androidx.core.app.Fragment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.chaturmasyakalaburgi2018.umchaturmasyainfo.R;
import org.chaturmasyakalaburgi2018.umchaturmasyainfo.activity.SplashActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class ViewFragment extends Fragment {

    private WebView myWebView;
    private ProgressDialog progressBar;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference rootdatabaseReference;
    String websiteName = null;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message message) {
            switch (message.what) {
                case 1:{
                    webViewGoBack();
                }break;
            }
        }
    };

    public ViewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_view, container, false);

        firebaseDatabase = FirebaseDatabase.getInstance();
        rootdatabaseReference = firebaseDatabase.getReference();
        rootdatabaseReference.child("UMUrls").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int count = 1;
                websiteName = dataSnapshot.child(String.valueOf(count)).child("urlUrl").getValue(String.class);
                Log.e("TAG url",""+ websiteName);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        });

        if (SplashActivity.website == null){
            if (SplashActivity.count == 1){
                Intent intent = new Intent(getActivity(),SplashActivity.class);
                startActivity(intent);
            } else {
                alertdialog();
            }

        }else {
            websiteName = SplashActivity.website;
        }
        myWebView = (WebView)v.findViewById(R.id.webViewHome);
        final AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        myWebView.loadUrl(websiteName);
        myWebView.setWebViewClient(new WebViewClient(){

            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                if (progressBar!= null && progressBar.isShowing()) {
                    progressBar.dismiss();
                }
                progressBar = ProgressDialog.show(getActivity(), "Loading", "Plz wait...");
            }

            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);

                return true;
            }

            public void onPageFinished(WebView view, String url) {
                if (progressBar.isShowing()) {
                    progressBar.dismiss();
                }
            }

            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                alertDialog.setTitle("No Internet Connection");
                alertDialog.setMessage("Plz On Your Data Connection");
                alertDialog.setCancelable(false);
                alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(getActivity(),SplashActivity.class);
                        startActivity(intent);
                        return;
                    }
                });
                alertDialog.show();
            }

        });

        myWebView.setOnKeyListener(new View.OnKeyListener(){

            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK
                        && event.getAction() == MotionEvent.ACTION_UP
                        && myWebView.canGoBack()) {
                    handler.sendEmptyMessage(1);
                    return true;
                }

                return false;
            }

        });
        return v;
    }

    private void alertdialog() {
        new androidx.appcompat.app.AlertDialog.Builder(getActivity())
                .setTitle("No Internet Connection")
                .setMessage("Plz On Your Data Connection")
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(getActivity(),SplashActivity.class);
                        startActivity(intent);
                    }
                }).show();
    }

    private void webViewGoBack(){
        myWebView.goBack();
    }
}
