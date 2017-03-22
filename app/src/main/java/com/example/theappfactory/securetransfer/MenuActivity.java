package com.example.theappfactory.securetransfer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

import java.io.File;
import java.security.PublicKey;

import Services.services.FileHandlers.FileReader1;
import Services.services.SHA.GenerateHash;
import Services.services.decryption.DecryptData1;
import Services.services.decryption.DecryptKey1;
import Services.services.decryption.RSADecryption;
import Services.services.decryption.StartDecryption1;

/* FireBase imports */
/* Own Imports */


/**
 * Created by peetenbart on 20-03-17.
 */

public class MenuActivity extends AppCompatActivity {
    private FirebaseAuth auth;
    private Button       inboxButton;
    private Database     database;
    public static TextView     sneakPreviewFileTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Button outboxButton = (Button)findViewById(R.id.outboxbutton);
        outboxButton.setEnabled(false);
        sneakPreviewFileTextView = (TextView)findViewById(R.id.textViewSneakPreview);

        auth = FirebaseAuth.getInstance();
        database = new Database();
        database.getDataFromFireBase("", "");

        this.inboxButton = (Button)findViewById(R.id.inboxButton);
        inboxButton.setText("INBOX: " + "0");
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
         * input:  View:view
         * output: None
         */

        decrypt1();
        calculateHash();


    }

    private void calculateHash() {
        //bereken hash boodschap
        byte[] calculatedHash = GenerateHash.CreateHash(new String("/storage/emulated/0/Android/data/decryptedFile.txt"));

        //decrypteer file3 met publicB
        StartDecryption1 startDec1 = new StartDecryption1();
        PublicKey publicKey_B = null;
        try {
            publicKey_B = startDec1.getPublic("/storage/emulated/0/Android/data/publicKey_Alice.txt", "RSA");
            byte[] encryptedHashFromFile = FileReader1.ReadBytesFromFile("/storage/emulated/0/Android/data/HashEncrypted.txt");
            byte[] decryptedHashFromFile = RSADecryption.DecryptRSA(encryptedHashFromFile, publicKey_B);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void decrypt1(){
        StartDecryption1 startEnc = new StartDecryption1();

        File encryptedKeyReceived = new File("/storage/emulated/0/Android/data/encryptedSecretKey.txt");
        File decreptedKeyFile = new File("/storage/emulated/0/Android/data/SecretKey.txt");
        File encryptedFileReceived = new File("/storage/emulated/0/Android/data/encryptedFile.txt");
        File decryptedFile = new File("/storage/emulated/0/Android/data/decryptedFile.txt");
        try {
            new DecryptKey1(startEnc.getPrivate("/storage/emulated/0/Android/data/privateKey_Bob.txt", "RSA"),
                    encryptedKeyReceived, decreptedKeyFile, "RSA");
            new DecryptData1(encryptedFileReceived, decryptedFile,
                    startEnc.getSecretKey("/storage/emulated/0/Android/data/SecretKey.txt", "AES"), "AES");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
