package com.example.theappfactory.securetransfer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/* FireBase imports */
import com.google.firebase.auth.FirebaseAuth;

/* Own Imports */
import Services.services.RSA.GenerateRSAKeys;


/**
 * Created by peetenbart on 20-03-17.
 */

public class MenuActivity extends AppCompatActivity {
    private FirebaseAuth auth;
    private Button       inboxButton;
    private Database     database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Button outboxButton = (Button)findViewById(R.id.outboxbutton);
        outboxButton.setEnabled(false);

        auth = FirebaseAuth.getInstance();
        database = new Database();
        database.getDataFromFireBase("", "");

        this.inboxButton = (Button)findViewById(R.id.inboxButton);
        inboxButton.setText("INBOX: " + "0");

        GenerateRSAKeys.CreateRSAKeys();
        UserObject.privateKey = GenerateRSAKeys.getPrivateKey();
        UserObject.publicKey = GenerateRSAKeys.getPublicKey();
    }

    public void sendFileButtonClick(View view){
        Intent intent = new Intent(this, SendActivity.class);
        startActivity(intent);
    }

    public void backButtonClick(View view){
        Intent loginIntent = new Intent(this, LoginActivity.class);
        auth.signOut();
        startActivity(loginIntent);
    }

    public void inboxButtonOnClick(View view){
        /** When this button is clicked the files will be retrieved from the server.
         * But since this is NOT in place yet we will work with hardcoded files which are
         * encrypted by the send button from the SendActivity class.
         *
         * input:  None
         * output: None
         */

        String desKeyFile =           "/storage/emulated/0/Android/data/DESKey.txt";
        String encryptedMessageFile = "/storage/emulated/0/Android/data/Test_encrypted1.txt";
        String encryptedDESKeyFile =  "/storage/emulated/0/Android/data/EncryptedDESKey.txt";;
        String hashCodeFile =         "/storage/emulated/0/Android/data/HashCodeFile.txt";
    }
}
