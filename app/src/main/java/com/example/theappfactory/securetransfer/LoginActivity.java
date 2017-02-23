package com.example.theappfactory.securetransfer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class LoginActivity extends AppCompatActivity {
    private EditText inputEmail, inputPassword;
    private Button loginButton;
    private Button signupButton;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Get Firebase auth instance
        auth = FirebaseAuth.getInstance();

        if (auth.getCurrentUser() != null) {
            Intent intent = new Intent(LoginActivity.this, MenuActivity.class);
            startActivity(intent);
            finish();
        }

        // Get UI Elements.
        inputEmail = (EditText) findViewById(R.id.loginemailtext);
        inputPassword = (EditText) findViewById(R.id.loginpaswordtext);
        loginButton = (Button) findViewById(R.id.loginLoginbutton);
        signupButton = (Button) findViewById(R.id.loginsignupButton);

        //Get Firebase auth instance
        auth = FirebaseAuth.getInstance();

        loginButton.setOnClickListener(this::loginButtonOnClick);
        signupButton.setOnClickListener(this::signupButtonClick);;
    }



    private void loginButtonOnClick(View view){
        String email = inputEmail.getText().toString();
        final String password = inputPassword.getText().toString();

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
            return;
        }

        Log.d("LOGIN EMAIL", email);
        Log.d("LOGIN PASWOORD", password);

        //authenticate user
        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.

                        Intent intent = new Intent(LoginActivity.this, MenuActivity.class);
                        startActivity(intent);
                        FirebaseUser user = auth.getCurrentUser();
                        Log.d("USER ID IS: ", user.getUid());
                        finish();
                });
    }

    private void signupButtonClick(View view){
        Intent intent = new Intent(this, CreateNewUserActivity.class);
        startActivity(intent);
    }

}
