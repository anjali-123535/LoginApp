package com.example.loginapp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.example.loginapp.R;
import com.example.loginapp.databinding.ActivityProfileBinding;

public class ProfileActivity extends AppCompatActivity {
SharedPreferences sharedPreferences;
    ActivityProfileBinding activityProfileBinding;
    private static final String IS_USER_LOGIN = "IsUserLoggedIn";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_profile);
          activityProfileBinding=DataBindingUtil.setContentView(this,R.layout.activity_profile);
         sharedPreferences=getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        activityProfileBinding.setUsername(sharedPreferences.getString("username","Username"));
        //the logout functionality will clear the user session so he will be redirected to the Login activity
         activityProfileBinding.btnLogout.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 SharedPreferences.Editor ed=sharedPreferences.edit();
                 ed.clear();
                 ed.apply();
                 // After logout redirect user to Login Activity
                 Intent i = new Intent(getApplicationContext(), MainActivity.class);
                 // Closing all the Activities
                 i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                 // Add new Flag to start new Activity
                 i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                 // Staring Login Activity
                startActivity(i);
             }
         });
    }
    // Check for login
    public boolean isUserLoggedIn(){
        return sharedPreferences.getBoolean(IS_USER_LOGIN, false);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //checking if user is already logged in
        if (!isUserLoggedIn()) {
            // user is not logged in redirect him to Login Activity
            Intent i = new Intent(this, MainActivity.class);

            // Closing all the Activities from stack
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            // Add new Flag to start new Activity
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            // Staring Login Activity
            startActivity(i);
            finish();
        }
        else
            activityProfileBinding.setUsername(sharedPreferences.getString("username",""));
    }
}