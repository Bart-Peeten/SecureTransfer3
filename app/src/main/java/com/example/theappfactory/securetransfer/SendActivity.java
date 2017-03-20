package com.example.theappfactory.securetransfer;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

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
            // Path of source txt file is for the moment hard coded to see if it works in totaly.
            // When there is time I need to find out how to get the right path.
            // For the moment the method is giving the wrong path name.
            DES.init("/storage/emulated/0/Android/data/test_bart.txt");
            DES.send(getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void backToMenuButtonClick(View view){
        Intent menuIntent = new Intent(this, MenuActivity.class);
        startActivity(menuIntent);
    }

    // Method to get the path of the chosen file.
    protected void onActivityResult(int requestCode, int resultCode, Intent resultData) {
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
                this.filePath = uri.getPath().toString();

                Log.d("////////FILE URL IS: ", this.filePath);
            }
        }
    }
}