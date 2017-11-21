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
public class ExcursionAdapter extends ArrayAdapter {

    public ExcursionAdapter(Context context, ArrayList<Excursion> excursions) {
        super(context,0,excursions);
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Excursion excursion = (Excursion) getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_excursion, parent, false);
        }
        // Lookup view for data population
        TextView exDesciption = (TextView) convertView.findViewById(R.id.exDescription);
        TextView exPrice = (TextView) convertView.findViewById(R.id.exPrice);
        TextView exTtile = (TextView) convertView.findViewById(R.id.exTitle);
        ImageView exImg = (ImageView) convertView.findViewById(R.id.exImage);

        final View finalConvertView = convertView;
        exImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finalConvertView.performLongClick();

            }
        });

        // Populate the data into the template view using the data object
        exDesciption.setText(excursion.description);
        exTtile.setText(excursion.title);
        exPrice.setText(excursion.price);
        exImg.setImageResource(resolveImageView(excursion.getImgName()));
        // Return the completed view to render on screen
        return convertView;
    }


    private int resolveImageView(String exImgName) {
        switch (exImgName) {
            case "Barcelona":
                return R.drawable.offer_1;
            case "Maldive":
                return R.drawable.offer_2;
            case "Thailand":
                return R.drawable.offer_3;
            default:
                return 0;

        }
    }


}

