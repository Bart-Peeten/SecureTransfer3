package com.example.theappfactory.securetransfer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class SendActivity extends AppCompatActivity {

    private static final int FILE_SELECT_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send);

        // Create instances of the buttons and textview
        View sendTextView = findViewById(R.id.sendTextView);
        View selectButton = findViewById(R.id.selectbutton);
        View sendButton = findViewById(R.id.sendbutton);
    }

    public void selectButtonClick(View view) {
        Intent intent = new Intent("com.sec.android.app.myfiles.PICK_DATA");
        intent.putExtra("CONTENT_TYPE", "*/*");
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        startActivityForResult(intent, FILE_SELECT_CODE);
    }

    // Method to get the path of the chosen file.
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        String Fpath = data.getDataString();
        // do somthing...
        super.onActivityResult(requestCode, resultCode, data);
    }
}