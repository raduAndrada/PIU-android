package com.utcluj.laborator_1_android;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ListActivity extends AppCompatActivity {

    private static final String BARCELONA_TITLE = "Barcelona, 3 nights";
    private static final String BARCELONA_DESC = "A beautiful city";
    private static final String BARCELONA_IMG_NAME = "Barcelona";

    private static final String MALDIVE_TITLE = "Maldive, 7 nights";
    private static final String MALDIVE_DESC = "A beautiful city";
    private static final String MALDIVE_IMG_NAME = "Maldive";

    private static final String THAILAND_TITLE = "Thailand, 10 nights";
    private static final String THAILAND_DESC = "A beautiful city";
    private static final String THAILAND_IMG_NAME = "Thailand";

    private static final String DESC_DEFAULT = "Description for the added offer";

    private static final String GO_BACK_TEXT = "Please confirm logout intention";
    private static final String CONFIRMATION = "Confirmation";
    private static final String CONFIRM = "Confirm";
    private static final String CANCEL = "Cancel";
    private static final String SELECTED_EXCURSION_FOR_EXTEND = "selectedExcursion";

    private static final String EXPANSION_COUNTER = "counter";
    private static final String EXPANSION_TRIP_POSITION = "tripPosition";
    private static final String EXPANSION_TRIP_IS_FAVORITE = "tripIsFavorite";



    private ArrayList<Trip> excursions = new ArrayList<Trip>();


    private static final Trip ex1 = new Trip(BARCELONA_TITLE, BARCELONA_DESC, "15$", BARCELONA_IMG_NAME);
    private static final Trip ex2 = new Trip(MALDIVE_TITLE, MALDIVE_DESC, "15$", MALDIVE_IMG_NAME);
    private static final Trip ex3 = new Trip(THAILAND_TITLE, THAILAND_DESC, "15$", THAILAND_IMG_NAME);

    private static final Map<Integer, Integer> tripAccessCounterMap = new HashMap<>();
    private static final Map<Integer, Boolean> tripFavoritesMap = new HashMap<>();

    private ExcursionAdapter adapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        adapter = new ExcursionAdapter(this, excursions);
        Button signOutBtn = (Button) findViewById(R.id.signOutId);

        signOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlertDialog();
            }
        });

        // Construct the data source

// Create the adapter to convert the array to views

// Attach the adapter to a ListView
        ListView listView = (ListView) findViewById(R.id.excursionsList);
        listView.setAdapter(adapter);


        excursions.add(ex1);
        tripAccessCounterMap.put(0, 0);
        excursions.add(ex2);
        tripAccessCounterMap.put(1, 0);
        excursions.add(ex3);
        tripAccessCounterMap.put(2, 0);


        adapter.notifyDataSetChanged();
        registerForContextMenu(listView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ListActivity.this, ExpandExcursion.class);
                intent.putExtra(SELECTED_EXCURSION_FOR_EXTEND, excursions.get(position));
                intent.putExtra(EXPANSION_COUNTER, tripAccessCounterMap.get(position));
                intent.putExtra(EXPANSION_TRIP_POSITION, position);

                startActivityForResult(intent, 0);
            }
        });
    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
// verificăm dacă meniul este creat pentru lista vizată
        if (v.getId() == R.id.excursionsList) {
// identificăm elementul selectat din listă
            AdapterView.AdapterContextMenuInfo info =
                    (AdapterView.AdapterContextMenuInfo) menuInfo;
            menu.setHeaderTitle(resolveListTitle(String.valueOf(info.position)));
// încărcăm structura vizuală a meniului
            getMenuInflater().inflate(R.menu.context_menu_trip, menu);
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
// accesarea informației atașate meniului contextual
        AdapterView.AdapterContextMenuInfo info =
                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
// identificarea elementului selectat din meniu, folosind ID-urile predefinite
        if (item.getItemId() == R.id.addOfferId) {

            Trip ex = new Trip(excursions.get(info.position).getTitle(), DESC_DEFAULT, "0$", BARCELONA_IMG_NAME);
            excursions.add(ex);
            adapter.add(ex);
            adapter.notifyDataSetChanged();
        } else if (item.getItemId() == R.id.removeOfferId) {

            adapter.remove(excursions.get(info.position));
            excursions.remove(info.position);
            adapter.notifyDataSetChanged();
        }
        return super.onContextItemSelected(item);
    }


    @Override
    public void onBackPressed() {
        showAlertDialog();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.list_view_options_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();


        if (id == R.id.reset_list_menu_item_id) {
            excursions = new ArrayList<>();

            excursions.add(ex1);
            excursions.add(ex2);
            excursions.add(ex3);
            adapter.clear();
            adapter.add(ex1);
            adapter.add(ex2);
            adapter.add(ex3);
            adapter.notifyDataSetChanged();
            return true;
        }
        if (id == R.id.clear_favorites_menu_item_id) {

            for (Trip excursion : excursions) {
                excursion.setIsFavourite(false);
            }
        }
        if(id == R.id.sign_out_btn_id)
        {

        }
        if(id == R.id.chat_with_us_btn_id)
        {
            Intent intent = new Intent(ListActivity.this, ChatActivity.class);


            startActivityForResult(intent, 0);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 0) {
            if (resultCode == Activity.RESULT_OK) {
                Integer displayedTimesCounter = (Integer) data.getIntExtra(EXPANSION_COUNTER, 0);
                Integer tripPosition = (Integer) data.getIntExtra(EXPANSION_TRIP_POSITION, 0);
                Boolean isTripFavorite = (Boolean) data.getBooleanExtra(EXPANSION_TRIP_IS_FAVORITE,false);
                tripAccessCounterMap.put(tripPosition, displayedTimesCounter);
                excursions.get(tripPosition).setIsFavourite(isTripFavorite);
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                // cod	executat	dacă	nu	se	returnează	date
            }
        }
    }

    private String resolveListTitle(String id) {
        switch (id) {
            case "0":
                return BARCELONA_TITLE;
            case "1":
                return MALDIVE_TITLE;
            case "2":
                return THAILAND_TITLE;
            default:
                return BARCELONA_TITLE;
        }
    }

    private void showAlertDialog() {
        final AlertDialog.Builder logoutConfirmation = new AlertDialog.Builder(this);
        logoutConfirmation
                .setTitle(CONFIRMATION)
                .setMessage(GO_BACK_TEXT)
                .setPositiveButton(CONFIRM, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Intent intent = new Intent(ListActivity.this, MainActivity.class);
                        startActivity(intent);

                    }
                })
                .setNegativeButton(CANCEL, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .show();
    }


}
