package com.example.theappfactory.securetransfer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class MenuActivity extends AppCompatActivity {
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Button outboxButton = (Button)findViewById(R.id.outboxbutton);
        outboxButton.setEnabled(false);

        auth = FirebaseAuth.getInstance();

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
}
