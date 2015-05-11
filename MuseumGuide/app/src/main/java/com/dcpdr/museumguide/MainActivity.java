package com.dcpdr.museumguide;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class MainActivity extends ActionBarActivity implements View.OnClickListener
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // set onClick listener on the button
        Button button = (Button) findViewById(R.id.enterButton);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        Intent nextActivityIntent = new Intent(this, MapsActivity.class);
        startActivity(nextActivityIntent);
    }
}
