package com.example.theappfactory.securetransfer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;


public class LoginActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.theappfactory.securetransfer.MESSAGE";
    EditText editTextName = (EditText)findViewById(R.id.editText_Name);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        /* Disable Login button if both fields are empty
        the addTextChangedListener needs to be implemented in order to disable or enable LoginButton
        if the textfields are filled in */
        View cancelButton = findViewById(R.id.cancelButton);
        View loginButton = findViewById(R.id.loginButton);
        if (this.fieldsFilledIn()) {
            loginButton.setEnabled(true);
        }
        else{
            loginButton.setEnabled(false);
        }
    }

    private boolean fieldsFilledIn(){
        /* This metthod needs to be implented in a later fase.
        For the moment it returns always true */
        return true;
    }

    /** Called when the user clicks the Login button */
    public void loginButtonClick(View view){
        Intent intent = new Intent(this, MenuActivity.class);
        String loginName = editTextName.getText().toString();
        UserObject.getUserObject().setUserName(loginName);
        startActivity(intent);
    }

    public void cancelButtonClick(){
        // TODO: need to be implemented.
    }
}
