package com.parse.starter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

public class UsersActivity extends AppCompatActivity {

    ListView usersListView;
    ArrayAdapter<String> usersArrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);

        usersListView = (ListView) findViewById(R.id.usersListView);
        ParseQuery<ParseUser> userQuery = ParseUser.getQuery();
        userQuery.whereNotEqualTo("username",ParseUser.getCurrentUser().getUsername());
        userQuery.addAscendingOrder("username");

        final ArrayList<String> usersArrayList = new ArrayList<>();
        userQuery.findInBackground(new FindCallback<ParseUser>() {
            public void done(List<ParseUser> results, ParseException e) {
                if (e!=null){
                    Toast.makeText(UsersActivity.this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    return;
                }
                if (results.size()>0) {
                    usersArrayList.clear();
                    for (ParseUser user : results) {
                        usersArrayList.add(user.getUsername());
                    }
                    usersArrayAdapter.notifyDataSetChanged();
                }
            }
        });



        // This is the array adapter, it takes the context of the activity as a
        // first parameter, the type of list view as a second parameter and your
        // array as a third parameter.
        usersArrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,usersArrayList );

        usersListView.setAdapter(usersArrayAdapter);
    }
}
