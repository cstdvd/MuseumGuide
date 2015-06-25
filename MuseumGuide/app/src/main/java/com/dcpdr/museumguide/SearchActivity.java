package com.dcpdr.museumguide;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;
// Search activity provides the list of pictures that can be visited
// Clicking on them, the activity makes the path between the location the device is and the
// destination
// Search activity is activated only if the device position is know, otherwise it's not possible
// to know the source state of the path
public class SearchActivity extends ActionBarActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        ArrayList<NavigableItem> pictures = getIntent().getParcelableArrayListExtra("Pictures");

        Collections.sort(pictures);

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
                setResult(Parameters.REQUEST_SEARCH, nextActivityIntent);
                finish();
            }
        });
    }
}
