/*
 * Copyright (c) 2015-present, Parse, LLC.
 * All rights reserved.
 *
 * This source code is licensed under the BSD-style license found in the
 * LICENSE file in the root directory of this source tree. An additional grant
 * of patent rights can be found in the PATENTS file in the same directory.
 */
package com.parse.starter;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
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

import static com.google.android.gms.analytics.internal.zzy.v;


public class MainActivity extends AppCompatActivity  {

  EditText usernameEditText,passwordEditText;
  Boolean signup=true;
  Button button;
  TextView switchTextView;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    usernameEditText = (EditText) findViewById(R.id.usernameEditText);
    passwordEditText = (EditText) findViewById(R.id.passwordEditText);
    passwordEditText.setOnKeyListener(new View.OnKeyListener() {
      @Override
      public boolean onKey(View view, int i, KeyEvent keyEvent) {
        if (i==KeyEvent.KEYCODE_ENTER && keyEvent.getAction()==KeyEvent.ACTION_DOWN){
          button.callOnClick();
        }
        return false;
      }
    });
    
    button = (Button) findViewById(R.id.button);
    switchTextView = (TextView) findViewById(R.id.switchTextView);


    ParseAnalytics.trackAppOpenedInBackground(getIntent());
  }


  public void signUp(View view) {
    if (usernameEditText.getText().toString().isEmpty() || passwordEditText.getText().toString().isEmpty()){
      Toast.makeText(this, "Username and password are required", Toast.LENGTH_SHORT).show();
      return;
    }

    final ParseUser user = new ParseUser();
    user.setUsername(usernameEditText.getText().toString());
    user.setPassword(passwordEditText.getText().toString());

    user.signUpInBackground(new SignUpCallback() {
      @Override
      public void done(ParseException e) {
        if (e!=null){
          Toast.makeText(MainActivity.this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
          e.printStackTrace();
          return;
        }
        Log.i("Sign Up ", "User: "+user.getUsername());
        Toast.makeText(MainActivity.this, "Sign Up User: "+user.getUsername(), Toast.LENGTH_SHORT).show();

      }
    });
  }

  public void login(View view) {
    if (usernameEditText.getText().toString().isEmpty() || passwordEditText.getText().toString().isEmpty()){
      Toast.makeText(this, "Username and password are required", Toast.LENGTH_SHORT).show();
      return;
    }

    ParseUser.logInInBackground(usernameEditText.getText().toString(), passwordEditText.getText().toString(), new LogInCallback() {
      @Override
      public void done(ParseUser user, ParseException e) {
        if (e!=null){
          Toast.makeText(MainActivity.this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
          e.printStackTrace();
          return;
        }
        Log.i("Login ", "User: "+user.getUsername());
      }
    });


  }

  public void switchLoginSignup(View view) {
    if (signup){
      button.setText("Login");
      switchTextView.setText("Or, Sign Up");
      button.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
          login(view);
        }
      });
    }else{
      button.setText("Sign Up");
      switchTextView.setText("Or, Login");
      button.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
          signUp(view);
        }
      });
    }
    signup = !signup;
  }
  public static void setKeyboard(Context context, View view,Boolean show) {
    InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
    if (show){
      imm.showSoftInputFromInputMethod(view.getWindowToken(), 0);
    }else {
      imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

  }

}