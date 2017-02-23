package com.example.theappfactory.securetransfer;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

/**
 * Created by peetenbart on 02-02-17.
 */

public class Database {
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference rootRef = database.getReference("User");
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

    public DatabaseReference getRootRef() { return rootRef; }

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

    public void getDataFromFireBase(String childString, String string){
        Log.w("DATABASE METHOD", "TRY TO GET CONTENT");
        rootRef.orderByChild("name").equalTo("Bart").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot childDataSnapshot : dataSnapshot.getChildren()) {
                    Log.w("PARENT: ", childDataSnapshot.getKey());
                    Log.w("VALUE: ", childDataSnapshot.child("name").getValue().toString());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    /* This method will only called once to create the database structure.
        This method is not needed anymore, the databse is filled correctly
        Like we do now. */
    public void uploadFirstTimeUser(){
        String key = "0";
        String firstName = "Bart";
        String lastName = "Peeten";
        String email = "bart.peeten@gmail.com";
        String userName = "bpeeten";
        String pasword = "switch2it";
        User user = new User(lastName, firstName, email, userName, pasword);
        HashMap<String, User> hashmap = new HashMap<>();
        hashmap.put(key, user);
        this.database.getReference("User").setValue(hashmap);
    }

}
