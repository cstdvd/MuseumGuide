package com.dcpdr.museumguide;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;

public class SearchActivity extends ActionBarActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        ArrayList<NavigableItem> pictures = getIntent().getParcelableArrayListExtra("Pictures");
        ArrayList<NavigableItem> states = getIntent().getParcelableArrayListExtra("States");

        Collections.sort(pictures);
        Collections.sort(states);

        pictures.addAll(states);

        final ListView listView = (ListView) findViewById(R.id.searchList);

        ItemsAdapter adapter = new ItemsAdapter(this, R.layout.listactivity_row, pictures);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            // Start Maps Activity and give it the selected navigable item
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent nextActivityIntent = new Intent(getApplicationContext(), MapsActivity.class);

                ArrayList<NavigableItem> selectedList = new ArrayList<>();
                NavigableItem selectedItem = (NavigableItem) adapterView.getItemAtPosition(i);
                selectedList.add(selectedItem);

                nextActivityIntent.putParcelableArrayListExtra("Search", selectedList);
                setResult(Parameters.REQUEST_SEARCH,nextActivityIntent);
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
