package com.dcpdr.museumguide;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.qozix.tileview.TileView;


public class MapsActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Create a TileView object
        TileView tileView = new TileView(this);
        // Setup the TileView
        tileView.setSize(Parameters.TILE_WIDTH, Parameters.TILE_HEIGHT);
        for (int i = 0; i < Parameters.DETAIL_SCALES.length; i++)
            tileView.addDetailLevel(Parameters.DETAIL_SCALES[i], Parameters.TILES_PATH + Parameters.DETAIL_SCALES[i] * 100 + "/%col%_%row%" + Parameters.TILE_EXTENSION, Parameters.SAMPLE_IMAGE, Parameters.TILE_SIZE, Parameters.TILE_SIZE);

        // Define the bounds using the map size in pixel
        tileView.defineRelativeBounds(0, 0, Parameters.TILE_HEIGHT, Parameters.TILE_WIDTH);
        // Display the TileView
        tileView.setScale(0);
        setContentView(tileView);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_maps, menu);
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
