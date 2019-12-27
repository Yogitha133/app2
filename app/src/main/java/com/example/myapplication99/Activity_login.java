package com.example.myapplication99;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class Activity_login extends AppCompatActivity {
    private EditText mEmailView;
    private EditText mPasswordView;

    private CheckBox checkBoxRememberMe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mEmailView= (EditText) findViewById(R.id.prompt_email);
                mPasswordView=(EditText) findViewById(R.id.prompt_password);
             mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                 @Override
                 public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                     if(id== R.id.login || id== Editor.IME_NULL)
                     {
                         attemptLogin();
                         return true;
                     }
                     return false;
                 }
             });

             Button mEmailSignInButton =(Button) findViewById(R.id.email_sign_in_button);
             mEmailSignInButton.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     attemptLogin();
                 }
             });
             checkBoxRememberMe =(CheckBox)findViewById(R.id.checkboxRememberMe)//* v will validate saved prefernces
        if(!new PrefManager(this).isUserLogedOut()){
            startHomeActivity();
        }
    }

    private void attemptLogin() {
        mEmailView.setError(null);
        mPasswordView.setError(null);
        String email =mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();

        boolean cancel=false;
        View focusView= null;

        if(!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView =mPasswordView;
            cancel = true;
        }

        if(TextUtils.isEmpty(email)){
            mEmailView.setError(getString(R.string.error_field_required));
            focusView= mEmailView;
            cancel = true;

        }
        else if(!isEmailValid(email)) {
            mEmailView.setError(getString(R.string.error_invalid_email));
            focusView= mEmailView;
            cancel = true;
        }

        if(cancel)
        {
            focusView.requestFocus();
        }
        else
        {
            if(checkBoxRememberMe.isChecked())
                saveLoginDetails(email,password);
            startHomeActivity();
        }
    }
    private void startHomeActivity()
    {
        Intent intent= new Intent(this,HomeActivity.class);
        startActivity(intent);
        finish();
    }
    private void saveLoginDetails(String email,String password) {
        new PrefManager(this).saveLoginDetails(email,password);
    }
    private boolean isEmailValid(String email){
        return email.contains("@");
    }
    private boolean isPasswordValid(String password) {
        return password.length()>4;
    }
}
