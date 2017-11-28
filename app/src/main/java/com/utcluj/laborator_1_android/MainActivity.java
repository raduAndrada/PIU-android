package com.utcluj.laborator_1_android;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String REQUIRED_PASSWORD = "andrada";
    private static final String REQUIRED_USERNAME = "andrada";


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button signInBtn = (Button) findViewById(R.id.signInBtnId);
        final EditText usernameEditText = (EditText) findViewById(R.id.usernameId);
        final EditText passwordEditText = (EditText) findViewById(R.id.passwordId);
        final TextView passwordErrorField = (TextView) findViewById(R.id.passwordWrongField);
        final TextView usernameErrorField = (TextView) findViewById(R.id.usernameErrorFieldId);
        final TextView loginMsg = (TextView) findViewById(R.id.loginMsgId);
        final ProgressBar loginProcessBar = (ProgressBar) findViewById(R.id.loginProcessBarId);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        passwordErrorField.setVisibility(View.INVISIBLE);
        passwordErrorField.setTextColor(Color.RED);
        usernameErrorField.setVisibility(View.INVISIBLE);
        usernameErrorField.setTextColor(Color.RED);
        loginMsg.setVisibility(View.INVISIBLE);
        loginProcessBar.setVisibility(View.GONE);

        signInBtn.setOnClickListener(new View.OnClickListener() {
                                         @Override
                                         public void onClick(View v) {
                                             boolean isOk = true;

                                             if (usernameEditText.getText() == null || usernameEditText.getText().toString().isEmpty()) {
                                                 usernameErrorField.setTextColor(Color.RED);
                                                 usernameErrorField.setText(R.string.username_not_empty);
                                                 usernameErrorField.setVisibility(View.VISIBLE);
                                                 isOk = false;
                                             } else if (usernameEditText.getText().length() <= 2) {
                                                 usernameErrorField.setTextColor(Color.RED);
                                                 usernameErrorField.setVisibility(View.VISIBLE);
                                                 isOk = false;

                                             } else {
                                                 usernameErrorField.setVisibility(View.INVISIBLE);
                                                 isOk = true;
                                             }
                                             if (passwordEditText.getText() == null || passwordEditText.getText().toString().isEmpty()) {
                                                 passwordErrorField.setTextColor(Color.RED);
                                                 passwordErrorField.setText(R.string.password_not_empty);
                                                 passwordErrorField.setVisibility(View.VISIBLE);
                                                 isOk = false;
                                             } else if (passwordEditText.getText().length() <= 2) {
                                                 passwordErrorField.setTextColor(Color.RED);
                                                 passwordErrorField.setVisibility(View.VISIBLE);
                                                 isOk = false;
                                             } else {
                                                 passwordErrorField.setVisibility(View.INVISIBLE);
                                                 isOk = isOk == false ? false : true;
                                             }
                                             if (isOk) {
                                                 if (usernameEditText.getText().toString().equals(REQUIRED_USERNAME) && passwordEditText.getText().toString().equals(REQUIRED_PASSWORD)) {
                                                     loginMsg.setText(R.string.login_msg_success);
                                                     loginMsg.setTextColor(Color.GREEN);
                                                     loginMsg.setVisibility(View.VISIBLE);

                                                     loginProcessBar.setVisibility(View.VISIBLE);
                                                     usernameEditText.setVisibility(View.GONE);
                                                     passwordEditText.setVisibility(View.GONE);
                                                     signInBtn.setVisibility(View.GONE);
                                                     loginMsg.setVisibility(View.GONE);
                                                     final Handler handler = new Handler();
                                                     handler.postDelayed(new Runnable() {
                                                         @Override
                                                         public void run() {

                                                             Intent intent = new Intent(MainActivity.this, ListActivity.class);
                                                             startActivity(intent);
                                                         }
                                                     }, 5000);

                                                 } else {
                                                     loginMsg.setText(R.string.login_msg_error);
                                                     loginMsg.setTextColor(Color.RED);
                                                     loginMsg.setVisibility(View.VISIBLE);
                                                 }
                                             }
                                         }


                                     }

        );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();


        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
