package com.utcluj.laborator_1_android;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ChatActivity extends AppCompatActivity {

    private static final String USERNAME = "username";
    private static final String TOKEN = "token";
    private static final String SHARED_PREFERENCES = "sharedPreferences";
    private static final String CGIS_URL ="https://cgisdev.utcluj.ro/moodle/chat-piu/";

    private ArrayList<ChatMessage> messagesList = new ArrayList<ChatMessage>();

    private Timer timer = new Timer();

    private ChatAdapter adapter = null;
    int counter = 0 ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        SharedPreferences sharedPref = ChatActivity.this.getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE);
        final String username = sharedPref.getString(USERNAME, "none");
        final String token = sharedPref.getString(TOKEN, "none");

        TextView usernameTextView = (TextView) findViewById(R.id.username_chat_text);
        usernameTextView.setText(username);

        adapter = new ChatAdapter(this, messagesList);

        ListView listView = (ListView) findViewById(R.id.messages_list);
        listView.setAdapter(adapter);


        ImageView starImg = (ImageView) findViewById(R.id.star_id);

        Button sendMsgBtn = (Button)  findViewById(R.id.button_send_message);
        final EditText msgContent = (EditText) findViewById(R.id.message_chat_edit_text_id);


        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                sendRequest(token);
            }
        }, 0, 5000);

        sendMsgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter ++ ;
                ChatMessage msg = new ChatMessage(username,msgContent.getText().toString(),new Date().toString(),ChatMessageType.DESIGN_1);
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(CGIS_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                ChatService chatService = retrofit.create(ChatService.class);
                chatService.putMessages("Bearer "+token, new Message(msg.getText())).enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        //nothing to be done here
                        response.code();
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable throwable) {
                        //nothing to be done here
                        throwable.printStackTrace();
                    }
                });

                messagesList.add(0,msg);
                msgContent.setText("");
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.context_menu_chat, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();


        if (id == R.id.design_1) {

            for (ChatMessage chatMessage : messagesList) {
                chatMessage.setType(ChatMessageType.DESIGN_1);

            }


            return true;
        }
        if (id == R.id.design_2) {
            for (ChatMessage chatMessage : messagesList) {
                chatMessage.setType(ChatMessageType.DESIGN_2);
            }


            return true;
        }
        if (id == R.id.both_designs) {
            int counter = 0;
            for (ChatMessage chatMessage : messagesList) {
                if (counter % 2 == 0) {
                    chatMessage.setType(ChatMessageType.DESIGN_1);
                }
                else {
                    chatMessage.setType(ChatMessageType.DESIGN_2);
                }

            }

            return true;
        }

        adapter.notifyDataSetChanged();
        return super.onOptionsItemSelected(item);
    }

    private void sendRequest(String token){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(CGIS_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ChatService chatService = retrofit.create(ChatService.class);

        chatService.getMessages("Bearer " + token).enqueue(new Callback<MessageList>() {
            @Override
            public void onResponse(Call<MessageList> call, Response<MessageList> response) {

                MessageList msgList = response.body();
                if(msgList != null){
                for (ChatMessage chatMessage : msgList.getMessageList()) {
                    if (msgList != null && chatMessage.getText() != null && chatMessage.getTimestamp() != null && chatMessage.getUsername() != null) {
                        messagesList.add(0, chatMessage);
                    }
                    adapter.notifyDataSetChanged();
                }

                }}

            @Override
            public void onFailure(Call<MessageList> call, Throwable throwable) {
                //nothing to be done here
            }
        });
    }

}
