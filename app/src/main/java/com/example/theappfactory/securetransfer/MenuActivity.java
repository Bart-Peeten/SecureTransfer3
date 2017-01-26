package com.example.theappfactory.securetransfer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        UserObject userobject = getIntent().getExtras().getParcelable(LoginActivity.EXTRA_MESSAGE);

        // Create instances of the buttons.
        View outboxButton = findViewById(R.id.outboxbutton);
        View sendButton = findViewById(R.id.sendbutton);
        View inboxButton = findViewById(R.id.inboxButton);
    }

    public void sendFileButtonClick(View view){
        Intent intent = new Intent(this, SendActivity.class);
        startActivity(intent);
    }
}
