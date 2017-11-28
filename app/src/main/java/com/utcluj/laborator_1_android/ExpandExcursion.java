package com.utcluj.laborator_1_android;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ExpandExcursion extends AppCompatActivity {


    private static final String SELECTED_EXCURSION_FOR_EXTEND= "selectedExcursion";
    private static final String EXPANSION_COUNTER= "counter";
    private static final String EXPANSION_TRIP_POSITION= "tripPosition";
    private static final String DETAILS_PAGE_DISPLAYED= "Details page displayed: ";

    private Integer displayedTimesCounter =  null;
    private Integer tripPosition =  null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expand_excursion);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final Trip trip =  (Trip) getIntent().getSerializableExtra(SELECTED_EXCURSION_FOR_EXTEND);
        displayedTimesCounter = (Integer) getIntent().getIntExtra(EXPANSION_COUNTER, 0);
        tripPosition = (Integer) getIntent().getIntExtra(EXPANSION_TRIP_POSITION, 0);

        final Button addToFavsBtn = (Button) findViewById(R.id.addToFavorites);
        final Button removeFromFavsBtn = (Button) findViewById(R.id.removeFromFavorites);
        final TextView exCounter = (TextView) findViewById(R.id.exAccessCounter);

        exCounter.setText(DETAILS_PAGE_DISPLAYED + displayedTimesCounter + " times");
        removeFromFavsBtn.setVisibility(View.GONE);


        addToFavsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                trip.setIsFavourite(true);
                addToFavsBtn.setVisibility(View.GONE);
                removeFromFavsBtn.setVisibility(View.VISIBLE);
            }
        });

        removeFromFavsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                trip.setIsFavourite(false);
                addToFavsBtn.setVisibility(View.VISIBLE);
                removeFromFavsBtn.setVisibility(View.GONE);
            }
        });


    }


}
