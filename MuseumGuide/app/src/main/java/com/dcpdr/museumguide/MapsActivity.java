package com.dcpdr.museumguide;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.qozix.tileview.TileView;


public class MapsActivity extends ActionBarActivity {

    // Constants definition
    private final int tileHeight = 2512;
    private final int tileWidth = 2288;
    private final int tileSize = 128;
    private final String tilesPath = "tiles/planimetria/";
    private final String sampleImage = "samples/planimetria/planimetria.jpg";
    private final String extension = ".jpg";
    private final float[] detailScales = {1.00f, 0.5f, 0.25f, 0.125f};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Create a TileView object
        TileView tileView = new TileView(this);
        // Setup the TileView
        tileView.setSize(tileHeight, tileWidth);
        for (int i = 0; i < detailScales.length; i++)
            tileView.addDetailLevel(detailScales[i], tilesPath + detailScales[i] * 100 + "/%col%_%row%" + extension, sampleImage, tileSize, tileSize);
        // Define the bounds using the map size in pixel
        tileView.defineRelativeBounds(0, 0, tileHeight, tileWidth);
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
