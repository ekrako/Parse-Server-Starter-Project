package com.parse.starter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;

public class UserFeedActivity extends AppCompatActivity {
    LinearLayout linerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_feed);

        linerLayout = (LinearLayout) findViewById(R.id.linLayout);
        final String username = getIntent().getStringExtra("username");
        setTitle(username + " Images");
        ParseQuery<ParseObject> imageQuery = new ParseQuery<ParseObject>("image");
        if (!username.isEmpty()) {
            imageQuery.whereEqualTo("username",username);
        }
        imageQuery.orderByDescending("createdAt");
        imageQuery.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if(e!=null){
                    e.printStackTrace();
                    Toast.makeText(UserFeedActivity.this, "failed to find images", Toast.LENGTH_SHORT).show();
                    finish();
                }
                if(objects.size()==0){
                    Toast.makeText(UserFeedActivity.this, username+" have no images", Toast.LENGTH_SHORT).show();
                    finish();
                }
                for (ParseObject object:objects){
                    ParseFile file = object.getParseFile("image");
                    file.getDataInBackground(new GetDataCallback() {
                        @Override
                        public void done(byte[] data, ParseException e) {
                            if (e!=null){
                                e.printStackTrace();
                                return;
                            }
                            if (data==null){
                                return;
                            }
                            Bitmap bitmap = BitmapFactory.decodeByteArray(data,0,data.length);
                            ImageView imageView = new ImageView( getApplicationContext());
                            imageView.setLayoutParams(new ViewGroup.LayoutParams(
                                    ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT
                            ));
                            imageView.setImageBitmap(bitmap);
                            linerLayout.addView(imageView);
                        }
                    });

                }
            }
        });


    }
}
