package com.example.theappfactory.securetransfer;

import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by peetenbart on 02-02-17.
 */

public class Database {
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference rootRef = database.getReference("User");
    private DatabaseReference userRef = rootRef.child("User");
    private long numberOfChilds;
    private FirebaseAuth auth = FirebaseAuth.getInstance();

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
        String key = user.getUserId();
        Log.w("The key is: ", key);

        userRef.child(key).setValue(user);
    }

    public String getDataFromFireBase(String childString, String string){
        Log.w("DATABASE METHOD", "TRY TO GET CONTENT");
        Log.d("HET USERID IS: ", auth.getCurrentUser().getUid());
        String returnString = "";
        rootRef.child("User").child(auth.getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                Log.d("HET PASWOORD IS: ", user.getPassword());
                Log.d("DE EMAIL IS: ", user.getEmail());
                Log.d("PRIVATE KEY IS: ", user.getPrivateKey().toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return returnString;
    }
}
