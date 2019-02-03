/*
 * Copyright (c) 2015-present, Parse, LLC.
 * All rights reserved.
 *
 * This source code is licensed under the BSD-style license found in the
 * LICENSE file in the root directory of this source tree. An additional grant
 * of patent rights can be found in the PATENTS file in the same directory.
 */
package com.parse.starter;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Switch;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseAnalytics;
import com.parse.ParseAnonymousUtils;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.parse.SignUpCallback;

import java.util.List;


public class MainActivity extends AppCompatActivity {


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

/* sign up
    final ParseUser user = new ParseUser();
    user.setUsername("ekrako");
    user.setPassword("password");

    user.signUpInBackground(new SignUpCallback() {
      @Override
      public void done(ParseException e) {
        if (e!=null){
          e.printStackTrace();
          return;
        }
        Log.i("Sign Up ", "User: "+user.getUsername());
        Toast.makeText(MainActivity.this, "Sign Up User: "+user.getUsername(), Toast.LENGTH_SHORT).show();

      }
    });
    //login
    ParseUser.logInInBackground("ekrako","pass11word",new LogInCallback(){

        @Override
        public void done(ParseUser user, ParseException e) {
            if (e!=null){
                e.printStackTrace();
                return;
            }
            Log.i("Login succesfully ", "User: "+user.getUsername());
            Toast.makeText(MainActivity.this, "Login User: "+user.getUsername(), Toast.LENGTH_SHORT).show();
        }
    });
    */
    ParseUser.logOut();
    if(ParseUser.getCurrentUser()!=null){
        Log.i("already logged in: ",ParseUser.getCurrentUser().getUsername());
    } else {
      Log.i("no signed in: ","none");
    }
    ParseAnalytics.trackAppOpenedInBackground(getIntent());
  }

}