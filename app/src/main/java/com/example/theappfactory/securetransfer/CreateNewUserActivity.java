package com.example.theappfactory.securetransfer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;


public class CreateNewUserActivity extends AppCompatActivity {
    private EditText firstnameText;
    private EditText lastnameText;
    private EditText emailText;
    private EditText usernameText;

    private String firstName;
    private String lastName;
    private String email;
    private String userName;
    private Database database;

    //private AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_user);

        // Create a database instance.
        this.database = new Database();

        // Get UI Elements.
        firstnameText = (EditText)findViewById(R.id.firstNameEditText);
        lastnameText = (EditText)findViewById(R.id.NameEditText);
        emailText = (EditText)findViewById(R.id.emailEditText);
        usernameText = (EditText)findViewById(R.id.usernamerEditText);

        // Fill the database with the first Dummy user.
        //database.uploadFirstTimeUser();
    }

    @Override
    protected void onStart(){
        super.onStart();

        database.getUserRef().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                database.setNumberOfChilds(dataSnapshot.getChildrenCount());
                Log.w("AMOUNT IS: ", String.valueOf(dataSnapshot.getChildrenCount()));
                Log.w("NumberOfChilds is: ", String.valueOf(database.getNumberOfChilds()));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    // If cancel button is clicked go back to login activity.
    public void cancelButtonClick(View view){
        Intent loginIntent = new Intent(this, LoginActivity.class);
        startActivity(loginIntent);
    }

    public void createUserButtonClick(View view){
        Intent loginWhenCreatedIntent = new Intent(this, LoginActivity.class);
        // Check if all Edtitexts are filled in.
        firstName = firstnameText.getText().toString();
        lastName = lastnameText.getText().toString();
        email = emailText.getText().toString();
        userName = usernameText.getText().toString();

        if (!firstName.equals("")     &&
                !lastName.equals("")  &&
                !email.equals("")     &&
                !userName.equals("")){
            User user = new User(lastName, firstName, email, userName);
            this.database.appendUserToExistingUserTree(user);
        }

        startActivity(loginWhenCreatedIntent);
    }
}
