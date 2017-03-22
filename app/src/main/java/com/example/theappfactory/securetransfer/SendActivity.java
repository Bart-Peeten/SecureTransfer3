package com.example.theappfactory.securetransfer;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import java.io.File;

import Services.services.KeyHandlers.GenerateSymmetricKey;
import Services.services.encryption.EncryptData1;
import Services.services.encryption.EncryptKey1;
import Services.services.encryption.StartEncryption1;

public class SendActivity extends AppCompatActivity {

    private static final int FILE_SELECT_CODE = 1;
    private String filePath = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send);
    }

    public void selectButtonClick(View view) {
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("*/*");
        startActivityForResult(intent, FILE_SELECT_CODE);
    }

    public void sendButtonClick(View view){
        try {
            /** In this method (Encrypt()) we will generate Symmetric key for the AES algorithm to
             * encrypt the file we want to send to the reciever.
             */
            //Encrypt();

            GenerateSymmetricKey genSK = new GenerateSymmetricKey(16, "AES");
            genSK.writeToFile("/storage/emulated/0/Android/data/secretKey.txt", genSK.getKey().getEncoded());

            encrypt1();

            // Path of source txt file is for the moment hard coded to see if it works in totaly.
            // When there is time I need to find out how to get the right path.
            // For the moment the method is giving the wrong path name.

            //DES.init("/storage/emulated/0/Android/data/test_bart.txt");
            //DES.send();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void encrypt1() throws Exception {
        StartEncryption1 startEnc = new StartEncryption1();

        File originalKeyFile = new File("/storage/emulated/0/Android/data/secretKey.txt");
        File encryptedKeyFile = new File("/storage/emulated/0/Android/data/encryptedSecretKey.txt");
        new EncryptKey1(startEnc.getPublic("/storage/emulated/0/Android/data/publicKey_Bob.txt", "RSA"),
                originalKeyFile, encryptedKeyFile, "RSA");

        File originalFile = new File("/storage/emulated/0/Android/data/test_bart.txt");
        File encryptedFile = new File("/storage/emulated/0/Android/data/encryptedFile.txt");
        new EncryptData1(originalFile, encryptedFile,
                startEnc.getSecretKey("/storage/emulated/0/Android/data/secretKey.txt", "AES"), "AES");
    }

    /*
    private void Encrypt() throws IOException, GeneralSecurityException {
        GenerateAESKey generateAESKey = new GenerateAESKey();
        FileWriter.writeBytesToFile("/storage/emulated/0/Android/data/secretDESKey.txt",
                generateAESKey.GetAESKey().getEncoded());

        StartEncryption startEncryption = new StartEncryption();
        File originalKeyFile = new File("/storage/emulated/0/Android/data/secretDESKey.txt");
        File encryptedKeyFile = new File("/storage/emulated/0/Android/data/encryptedDESKey.txt");

        new EncryptKey(FileReader.readPublicKey("/storage/emulated/0/Android/data/publicKey_receiver.txt"),
                originalKeyFile,
                encryptedKeyFile,
                "RSA");

        File originalFile = new File("/storage/emulated/0/Android/data/test_bart.txt");
        System.out.println("/////DES KEY IS: " + generateAESKey.GetAESKey());
        System.out.println("/////DES Key IS: " + generateAESKey.GetAESKey().getEncoded());
        File encryptedFile = new File("/storage/emulated/0/Android/data/test_bartEncrypted.txt");
        new EncryptData(originalFile,
                encryptedFile,
                generateAESKey.GetAESKey(),
                "AES");
    }
    */


    public void backToMenuButtonClick(View view){
        Intent menuIntent = new Intent(this, MenuActivity.class);
        startActivity(menuIntent);
    }



    protected void onActivityResult(int requestCode, int resultCode, Intent resultData) {
        /** Method to get the path of the chosen file.
         * Input : int:requestCode
         *         int:resultCode
         *         Intent:resultData
         *
         * Output: None
         */
        // The ACTION_OPEN_DOCUMENT intent was sent with the request code
        // READ_REQUEST_CODE. If the request code seen here doesn't match, it's the
        // response to some other intent, and the code below shouldn't run at all.

        if (resultCode == Activity.RESULT_OK) {
            // The document selected by the user won't be returned in the intent.
            // Instead, a URI to that document will be contained in the return intent
            // provided to this method as a parameter.
            // Pull that URI using resultData.getData().
            Uri uri = null;
            if (resultData != null) {
                uri = resultData.getData();
                //this.filePath = uri.getPath().toString();

                Log.d("////////FILE URL IS: ", uri.getPath());
                Log.d("///////FILE URL IS: ", uri.getEncodedPath());
            }
        }
    }
}