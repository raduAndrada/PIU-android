package com.utcluj.laborator_1_android;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final String CGIS_URL ="https://cgisdev.utcluj.ro/moodle/chat-piu/";
    private static final String USERNAME = "username";
    private static final String SHARED_PREFERENCES = "sharedPreferences";
    private static final String PASSWORD = "password";
    private static final String TOKEN = "token";

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

                                             if (usernameEditText.getText() == null || usernameEditText.getText().toString().isEmpty()) {
                                                 usernameErrorField.setTextColor(Color.RED);
                                                 usernameErrorField.setText(R.string.username_not_empty);
                                                 usernameErrorField.setVisibility(View.VISIBLE);
                                                ;
                                             } else if (usernameEditText.getText().length() <= 2) {
                                                 usernameErrorField.setTextColor(Color.RED);
                                                 usernameErrorField.setVisibility(View.VISIBLE);


                                             } else {
                                                 usernameErrorField.setVisibility(View.INVISIBLE);

                                             }
                                             if (passwordEditText.getText() == null || passwordEditText.getText().toString().isEmpty()) {
                                                 passwordErrorField.setTextColor(Color.RED);
                                                 passwordErrorField.setText(R.string.password_not_empty);
                                                 passwordErrorField.setVisibility(View.VISIBLE);

                                             } else if (passwordEditText.getText().length() <= 2) {
                                                 passwordErrorField.setTextColor(Color.RED);
                                                 passwordErrorField.setVisibility(View.VISIBLE);

                                             } else {
                                                 passwordErrorField.setVisibility(View.INVISIBLE);

                                             }
                                             Retrofit retrofit = new Retrofit.Builder()
                                                     .baseUrl(CGIS_URL)
                                                     .addConverterFactory(GsonConverterFactory.create())
                                                     .build();
                                             AuthenticationService authService = retrofit.create(AuthenticationService.class);
                                             authService.loginUser(new Credentials(usernameEditText.getText().toString(), passwordEditText.getText().toString())).enqueue(new Callback<User>() {


                                                 @Override
                                                 public void onResponse(Call<User> call, Response<User> response) {

                                                     if(response.isSuccessful()){

                                                         loginMsg.setText(R.string.login_msg_success);
                                                         loginMsg.setTextColor(Color.GREEN);
                                                         loginMsg.setVisibility(View.VISIBLE);

                                                         loginProcessBar.setVisibility(View.VISIBLE);
                                                         usernameEditText.setVisibility(View.GONE);
                                                         passwordEditText.setVisibility(View.GONE);
                                                         signInBtn.setVisibility(View.GONE);

                                                         SharedPreferences sharedPref = getApplicationContext().getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE);
                                                         SharedPreferences.Editor editor = sharedPref.edit();
                                                         editor.putString(USERNAME,usernameEditText.getText().toString());
                                                         editor.putString(PASSWORD,passwordEditText.getText().toString());
                                                         editor.putString(TOKEN,response.body().token);
                                                         editor.commit();

                                                         loginMsg.setVisibility(View.GONE);
                                                         final Handler handler = new Handler();
                                                         handler.postDelayed(new Runnable() {
                                                             @Override
                                                             public void run() {

                                                                 Intent intent = new Intent(MainActivity.this, ListActivity.class);
                                                                 //intent.putExtra(PASSWORD,passwordEditText.getText().toString());
                                                                 startActivity(intent);
                                                             }
                                                         }, 5000);

                                                     }
                                                     else {

                                                             loginMsg.setText(R.string.login_msg_error);
                                                             loginMsg.setTextColor(Color.RED);
                                                             loginMsg.setVisibility(View.VISIBLE);
                                                         }

                                                 }

                                                 @Override
                                                 public void onFailure(Call<User> call, Throwable t) {
                                                     loginMsg.setText(R.string.login_msg_error);
                                                     loginMsg.setTextColor(Color.RED);
                                                     loginMsg.setVisibility(View.VISIBLE);
                                                 }
                                             });


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
