package com.dcpdr.museumguide;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
// The main activity includes only the MuseumGuide logo
// Clicking on "enter" it will starts the map activity
public class MainActivity extends ActionBarActivity implements View.OnClickListener
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // set onClick listener on the button
        Typeface font = Typeface.createFromAsset(this.getAssets(),"heorot.ttf");
        Button button = (Button) findViewById(R.id.enterButton);
        button.setTypeface(font);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        Intent nextActivityIntent = new Intent(this, MapsActivity.class);
        startActivity(nextActivityIntent);
    }
}
