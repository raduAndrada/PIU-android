package com.utcluj.laborator_1_android;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Student on 11/21/2017.
 */
public class ChatAdapter extends ArrayAdapter {


    public ChatAdapter(Context context, ArrayList<ChatMessage> messages) {
        super(context,0,messages);
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        final ChatMessage message = (ChatMessage) getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.message, parent, false);
        }
        // Lookup view for data population


        TextView chatSender = (TextView) convertView.findViewById(R.id.message_sender);
        TextView chatText = (TextView) convertView.findViewById(R.id.message_text);
        TextView chatDate = (TextView) convertView.findViewById(R.id.message_date);

        final ImageView starView = (ImageView) convertView.findViewById(R.id.star_id);
        starView.setVisibility(View.INVISIBLE);

        final View finalConvertView = convertView;


        chatSender.setText(message.getUsername());
        chatDate.setText(message.getSentTime().toString());
        chatText.setText(message.getMessage());

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(starView.getVisibility() == View.INVISIBLE) starView.setVisibility(View.VISIBLE);
                else starView.setVisibility(View.INVISIBLE);
            }
        });
        if(message.getType() == ChatMessageType.DESIGN_1)
        {
            chatDate.setVisibility(View.VISIBLE);
        }
        if(message.getType() == ChatMessageType.DESIGN_2)
        {
            chatDate.setVisibility(View.GONE);
        }

        return convertView;
    }


}

