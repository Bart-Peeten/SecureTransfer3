package com.example.theappfactory.securetransfer;

import android.util.Log;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

/**
 * Created by peetenbart on 02-02-17.
 */

public class Database {
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference rootRef = database.getReference();
    private DatabaseReference userRef = rootRef.child("User");
    private long numberOfChilds;

    public Database() {
    }

    public long getNumberOfChilds() {
        return numberOfChilds;
    }

    public DatabaseReference getUserRef() {
        return userRef;
    }

    public void setNumberOfChilds(long numberOfChilds) {
        this.numberOfChilds = numberOfChilds;
    }

    /* appends the new User to the user node
       firebase supplies the key - (getKey()) */
    public void appendUserToExistingUserTree(User user){
        String key = String.valueOf(numberOfChilds);
        Log.w("The key is: ", key);

        userRef.child(key).setValue(user);
    }

    /* This method will only called once to create the database structure.*/
    public void uploadFirstTimeUser(){
        String key = "0";
        String firstName = "Bart";
        String lastName = "Peeten";
        String email = "bart.peeten@gmail.com";
        String userName = "bpeeten";
        User user = new User(lastName, firstName, email, userName);
        HashMap<String, User> hashmap = new HashMap<>();
        hashmap.put(key, user);
        this.database.getReference("User").setValue(hashmap);
    }
}
