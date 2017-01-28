package com.example.theappfactory.securetransfer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class CreateNewUserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_user);
    }

    // If cancel button is clicked go back to login activity.
    public void cancelButtonClick(View view){
        Intent loginIntent = new Intent(this, LoginActivity.class);
        startActivity(loginIntent);
    }

    public void createUserButtonClick(View view){
        Intent loginWhenCreatedIntent = new Intent(this, LoginActivity.class);
        // Here needs to come code or a method call to store the new user
        // data in the database.
        startActivity(loginWhenCreatedIntent);
    }
}
