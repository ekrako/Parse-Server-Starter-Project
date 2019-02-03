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

import java.util.List;


public class MainActivity extends AppCompatActivity {


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    /*
    ParseObject score = new ParseObject("Score");
    score.put("username","eran");
    score.put("score",45);
    score.saveInBackground(new SaveCallback() {
      @Override
      public void done(ParseException e) {
        if (e ==null){
          Log.i("Succes","we Saved the score");
          Toast.makeText(MainActivity.this, "score saved", Toast.LENGTH_SHORT).show();
        }else{
          e.printStackTrace();
        }
      }
    });

    ParseQuery<ParseObject> pq = ParseQuery.getQuery("Score");

    pq.getInBackground("IVKZZ4oq5Z", new GetCallback<ParseObject>() {
      @Override
      public void done(ParseObject object, ParseException e) {
        if (e ==null && object != null ){
          Log.i("Success","we get the score "+object.getString("username")+" with score of "+String.valueOf(object.getInt("score")));
          Toast.makeText(MainActivity.this, object.getString("username")+" with score of "+String.valueOf(object.getInt("score")), Toast.LENGTH_SHORT).show();
        }else{
          e.printStackTrace();
        }
      }
    });
    ParseObject tweet = new ParseObject("Tweet");
    tweet.put("username","ekrako");
    tweet.put("tweet","My Firest tweeet");
    tweet.saveInBackground(new SaveCallback() {
      @Override
      public void done(ParseException e) {
        if (e!=null){
          e.printStackTrace();
          return;
        }
        Log.i("parse object","tweet saved");
      }
    });
    ParseQuery<ParseObject> query = ParseQuery.getQuery("Tweet");
     query.getInBackground("xyFJew6axl", new GetCallback<ParseObject>() {
       @Override
       public void done(ParseObject object, ParseException e) {
         if (e!=null){
           e.printStackTrace();
           return;
         }
         Log.i("Success","we get the score "+object.getString("username")+" just tweeted: "+object.getString("tweet"));
         Toast.makeText(MainActivity.this, object.getString("username")+" just tweeted: "+object.getString("tweet"), Toast.LENGTH_SHORT).show();
         object.put("tweet","just editing");
         object.saveInBackground();
       }
     });*/
    ParseQuery<ParseObject> query = ParseQuery.getQuery("Score");
    query.whereLessThan("score",50);
    query.findInBackground(new FindCallback<ParseObject>() {
      @Override
      public void done(List<ParseObject> objects, ParseException e) {
        if (e!=null){
          e.printStackTrace();
          return;
        }
        if (objects.size()<1){
          Log.i("no Objects","no object found in parse");
          return;
        }
        for (ParseObject object:objects){
          Integer score = object.getInt("score");
          object.put("score",score+20);
          object.saveInBackground();
          Log.i("username",object.getString("username"));
          Log.i("score",Integer.toString(object.getInt("score")));
        }
      }
    });
    ParseAnalytics.trackAppOpenedInBackground(getIntent());
  }

}