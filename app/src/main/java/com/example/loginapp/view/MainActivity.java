package com.example.loginapp.view;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.loginapp.R;
import com.example.loginapp.databinding.ActivityMainBinding;
import com.example.loginapp.model.LoginUser;
import com.example.loginapp.viewmodel.MainViewModel;

import java.util.Objects;
import java.util.Observable;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getName();
    SharedPreferences sharedPreferences;
    private MainViewModel mainViewModel;
    private static final String IS_USER_LOGIN = "IsUserLoggedIn";
    public static final String MyPREFERENCES = "MyPrefs" ;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        sharedPreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        final ActivityMainBinding activityMainBinding=DataBindingUtil .setContentView(this,R.layout.activity_main);
               mainViewModel=ViewModelProviders.of(this).get(MainViewModel.class);
               activityMainBinding.setMain(mainViewModel);
               activityMainBinding.setLifecycleOwner(this);

              activityMainBinding.btnLogin.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                    //  mainViewModel.getUser();
                       String username=mainViewModel.getUsername().getValue();
                       String password=mainViewModel.getPassword().getValue();
                       //checking if both the entries are filled or not
                      if(TextUtils.isEmpty(username) || TextUtils.isEmpty(password))
                          Toast.makeText(getApplicationContext(),"Please fill both the fields",Toast.LENGTH_SHORT).show();
                      else{
                          SharedPreferences.Editor editor=sharedPreferences.edit();
                          editor.putString("username",username);
                          editor.putBoolean(IS_USER_LOGIN,true);
                          editor.apply();
                          Log.d(TAG,"User created"+sharedPreferences.getString("username",""));
                          //if the user entered both the entries he will be directed to Profile Activity
                          Intent i= new Intent(MainActivity.this,ProfileActivity.class);
                          i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                          // Add new Flag to start new Activity
                          i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                          startActivity(i);
                          finish();
                      }
                  }
              });
    }
}