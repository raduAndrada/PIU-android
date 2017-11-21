package com.utcluj.laborator_1_android;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity  {

    private static final String BARCELONA_TITLE = "Barcelona, 3 nights";
    private static final String BARCELONA_DESC = "A beautiful city";
    private static final String BARCELONA_IMG_NAME = "Barcelona";

    private static final String MALDIVE_TITLE = "Maldive, 7 nights";
    private static final String MALDIVE_DESC = "A beautiful city";
    private static final String MALDIVE_IMG_NAME = "Maldive";

    private static final String THAILAND_TITLE = "Thailand, 10 nights";
    private static final String THAILAND_DESC = "A beautiful city";
    private static final String THAILAND_IMG_NAME = "Thailand";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Construct the data source
        ArrayList<Trip> excursions = new ArrayList<Trip>();
// Create the adapter to convert the array to views
        ExcursionAdapter adapter = new ExcursionAdapter(this, excursions);
// Attach the adapter to a ListView
        ListView listView = (ListView) findViewById(R.id.excursionsList);
        listView.setAdapter(adapter);


        Trip ex1 = new Trip(BARCELONA_TITLE, BARCELONA_DESC, "15$", BARCELONA_IMG_NAME);
        Trip ex2 = new Trip(MALDIVE_TITLE, MALDIVE_DESC, "15$", MALDIVE_IMG_NAME);
        Trip ex3 = new Trip(THAILAND_TITLE, THAILAND_DESC, "15$", THAILAND_IMG_NAME);
        adapter.add(ex1);
        adapter.add(ex2);
        adapter.add(ex3);

        adapter.notifyDataSetChanged();
        registerForContextMenu(listView);

    }



    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo)
    {
        super.onCreateContextMenu(menu, v, menuInfo);
// verificăm dacă meniul este creat pentru lista vizată
        if (v.getId()==R.id.excursionsList)
        {
// identificăm elementul selectat din listă
            AdapterView.AdapterContextMenuInfo info =
                    (AdapterView.AdapterContextMenuInfo)menuInfo;
            menu.setHeaderTitle(rezolveListTitle(String.valueOf(info.position)));
// încărcăm structura vizuală a meniului
            getMenuInflater().inflate(R.menu.context_menu_trip, menu);
        }
    }
    @Override
    public boolean onContextItemSelected(MenuItem item)
    {
// accesarea informației atașate meniului contextual
        AdapterView.AdapterContextMenuInfo info =
                (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
// identificarea elementului selectat din meniu, folosind ID-urile predefinite
        if(item.getItemId() == R.id.addOfferId)
        {
        }
        else if(item.getItemId() == R.id.removeOfferId)
        {
        }
        return super.onContextItemSelected(item);
    }

    String rezolveListTitle(String id){
        switch (id){
            case "0": return BARCELONA_TITLE;
            case "1" :return MALDIVE_TITLE;
            case "2": return THAILAND_TITLE;
            default:
                return BARCELONA_TITLE;
        }
    }

}
