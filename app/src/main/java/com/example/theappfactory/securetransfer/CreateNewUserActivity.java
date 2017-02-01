package com.example.theappfactory.securetransfer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class CreateNewUserActivity extends AppCompatActivity {
    private EditText firstnameText;
    private EditText lastnameText;
    private EditText emailText;
    private EditText usernameText;

    private String firstName;
    private String lastName;
    private String email;
    private String userName;
    private long numberOfChilds;

    //private AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);

    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference rootRef = database.getReference();
    private DatabaseReference userRef = rootRef.child("User");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_user);

        // Get UI Elements.
        firstnameText = (EditText)findViewById(R.id.firstNameEditText);
        lastnameText = (EditText)findViewById(R.id.NameEditText);
        emailText = (EditText)findViewById(R.id.emailEditText);
        usernameText = (EditText)findViewById(R.id.usernamerEditText);

        // Fill the database with the first Dummy user.
        //uploadFirstTimeUser(database);

    }

    @Override
    protected void onStart(){
        super.onStart();

        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                numberOfChilds = dataSnapshot.getChildrenCount();
                Log.w("AMOUNT IS: ", String.valueOf(dataSnapshot.getChildrenCount()));

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
            appendUserToExistingUserTree(userRef);
        }

        startActivity(loginWhenCreatedIntent);
    }

    /* appends the new User to the user node
       firebase supplies the key - (getKey()) */
    private void appendUserToExistingUserTree(DatabaseReference userReference){
        String key = String.valueOf(numberOfChilds);
        Log.w("The key is: ", key);
        User user = new User(lastName, firstName, email, userName);

        userReference.child(key).setValue(user);
    }

    /* This method will only called once to create the database structure.*/
    private void uploadFirstTimeUser(FirebaseDatabase database){
        String key = "0";
        firstName = "Bart";
        lastName = "Peeten";
        email = "bart.peeten@gmail.com";
        userName = "bpeeten";
        User user = new User(lastName, firstName, email, userName);
        HashMap<String, User> hashmap = new HashMap<>();
        hashmap.put(key, user);
        database.getReference("User").setValue(hashmap);
    }
}
