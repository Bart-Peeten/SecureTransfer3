package com.example.theappfactory.securetransfer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;

/* FireBase imports */
/* Own Imports */


/**
 * Created by peetenbart on 20-03-17.
 */

public class MenuActivity extends AppCompatActivity {
    private FirebaseAuth auth;
    private Button       inboxButton;
    private Database     database;
    private PrivateKey   privateKeySender;
    private PublicKey    publicKeySender;
    private PrivateKey   privateKeyReceiver;
    private PublicKey    publicKeyReceiver;

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

        generateSenderRSAKeys(publicKeySender, privateKeySender);
        generateSenderRSAKeys(publicKeyReceiver, privateKeyReceiver);
        writeSenderRSAKeyToFile(publicKeySender, privateKeySender, "/storage/emulated/0/Android/data/SenderRSAKey.txt");
        writeSenderRSAKeyToFile(publicKeyReceiver, privateKeyReceiver, "/storage/emulated/0/Android/data/RecieverRSAKey.txt");


    }

    private void writeSenderRSAKeyToFile(PublicKey publicKey, PrivateKey privateKey, String filePath) {
    /* save the public key in a file */
        byte[] publicKeyEncodedkey = publicKey.getEncoded();
        byte[] privateKeyEncodedkey = privateKey.getEncoded();
        FileOutputStream keyfos = null;
        try {
            keyfos = new FileOutputStream(filePath);
            keyfos.write(publicKeyEncodedkey);
            keyfos.write(privateKeyEncodedkey);
            keyfos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void generateSenderRSAKeys(PublicKey publicKey, PrivateKey privateKey) {
        try {
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("DSA", "SUN");
            SecureRandom random = null;
            random = SecureRandom.getInstance("SHA1PRNG", "SUN");
            keyGen.initialize(1024, random);

            KeyPair pair = keyGen.generateKeyPair();
            privateKey = pair.getPrivate();
            publicKey = pair.getPublic();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        }
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
