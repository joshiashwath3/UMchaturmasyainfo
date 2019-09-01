package org.chaturmasyakalaburgi2018.umchaturmasyainfo.activity;

import android.content.DialogInterface;
import android.content.Intent;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.chaturmasyakalaburgi2018.umchaturmasyainfo.R;
import org.chaturmasyakalaburgi2018.umchaturmasyainfo.common.SessionManagement;
import org.chaturmasyakalaburgi2018.umchaturmasyainfo.model.UMCUser;

import java.util.HashMap;

public class FeedbackActivity extends AppCompatActivity {

    EditText editTextOther;
    EditText editTextFeedback;
    String typeSelected;
    Spinner categorySpinner;
    SessionManagement session;
    Button button;

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference rootdatabaseReference;

    String loginName;
    String loginContact;
    String loginEmail;

    String[] list= {"Today's Program","Overall Program","Any Suggestions","Other"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        firebaseDatabase = FirebaseDatabase.getInstance();
        rootdatabaseReference = firebaseDatabase.getReference();
        session = new SessionManagement(getApplicationContext());

        editTextOther = (EditText)findViewById(R.id.user_other_feedback);
        editTextFeedback = (EditText)findViewById(R.id.user_feedback);
        categorySpinner = (Spinner)findViewById(R.id.user_spinner);
        button = (Button)findViewById(R.id.button);
        editTextOther.setVisibility(View.INVISIBLE);

        ArrayAdapter arrayAdapter = new ArrayAdapter(FeedbackActivity.this,android.R.layout.simple_spinner_item, list);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setAdapter(arrayAdapter);

        HashMap<String, String> loginUser = session.getUserDetails();
        loginName = loginUser.get(SessionManagement.KEY_NAME);
        loginContact = loginUser.get(SessionManagement.KEY_MOBILE_N0);
        loginEmail = loginUser.get(SessionManagement.KEY_Email);

        if (loginName == null){
            editUser();
        }

        categorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                typeSelected = parent.getItemAtPosition(position).toString();
                if (typeSelected.equals("Other")){
                    editTextOther.setVisibility(View.VISIBLE);
                } else {
                    editTextOther.setVisibility(View.INVISIBLE);
                }
                Log.e("typeSelected",typeSelected);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String feedback = editTextFeedback.getText().toString();
                if (editTextOther.equals("Other")){
                    typeSelected = editTextOther.getText().toString();
                }


                if (feedback.trim().length() > 0){

                    UMCUser user = new UMCUser();
                    user.setUserId(String.valueOf(System.currentTimeMillis()));
                    user.setUserFeedbackType(typeSelected);
                    user.setUserFeedback(feedback);
                    user.setUserFullName(loginName);
                    user.setUserContact(loginContact);

                    rootdatabaseReference.child("UMCFeedBack").child(String.valueOf(user.getUserContact())).child(user.getUserId()).setValue(user);
                    Toast.makeText(FeedbackActivity.this,"User add successfully",Toast.LENGTH_SHORT).show();

                    finish();
                } else {
                    if (feedback.equals("")) {
                        editTextFeedback.setError("Empty Filed");
                    }
                  }
                }
        });


    }

    private void editUser(){

        View alertLayout = LayoutInflater.from(FeedbackActivity.this)
                .inflate(R.layout.add_user,null);
        final EditText editTextFirstName = alertLayout.findViewById(R.id.user_other_feedback);
        final EditText editTextContactOne = alertLayout.findViewById(R.id.user_feedback);
        final EditText editTextEmail = alertLayout.findViewById(R.id.user_email);

        new AlertDialog.Builder(FeedbackActivity.this).setView(alertLayout)
                .setView(alertLayout)
                .setCancelable(false)
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        Toast.makeText(FeedbackActivity.this, "Cancel clicked", Toast.LENGTH_SHORT).show();
                    }
                })
                .setPositiveButton("Save", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(final DialogInterface dialog, int which) {

                        loginName = editTextFirstName.getText().toString();
                        loginContact = editTextContactOne.getText().toString();
                        loginEmail = editTextEmail.getText().toString();

                        if (loginContact.trim().length() > 0){

                        session.createLoginSession(loginName, loginContact,loginEmail);

                        UMCUser user = new UMCUser();
                        user.setUserFullName(loginName);
                        user.setUserContact(loginContact);
                        user.setUserEmail(loginEmail);

                        rootdatabaseReference.child("UMCUser").child(String.valueOf(user.getUserContact())).setValue(user);
                        Toast.makeText(FeedbackActivity.this,"User add successfully",Toast.LENGTH_SHORT).show();
                        finish();
                        startActivity(getIntent());

                        } else {
                            if (editTextContactOne.equals("")) {
                                editTextFeedback.setError("Empty Filed");
                            }
                        }
                    }
                }).create().show();
    }
}
