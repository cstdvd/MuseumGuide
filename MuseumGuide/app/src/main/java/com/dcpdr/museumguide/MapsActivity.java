package com.dcpdr.museumguide;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import com.estimote.sdk.Beacon;
import com.estimote.sdk.BeaconManager;
import com.estimote.sdk.Region;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class MapsActivity extends ActionBarActivity {

    private static final int REQUEST_ENABLE_BT = 1234;

    private XTileView tileView;
    private ImageView myPosition;

    private BeaconManager beaconManager;

    private MapGraph createGraph(Document document, int level)
    {
        Element multiLayeredGraph = (Element) document.getElementsByTagName(Parameters.GML_MLG).item(0);
        Element spaceLayers = (Element) multiLayeredGraph.getElementsByTagName(Parameters.GML_SLS).item(0);
        NodeList spaceLayersMembers = spaceLayers.getElementsByTagName(Parameters.GML_SLM);

        Element roomSpaceLayerMember = (Element) spaceLayersMembers.item(level);
        Element roomSpaceLayer = (Element) roomSpaceLayerMember.getElementsByTagName(Parameters.GML_SL).item(0);
        // get nodes
        Element roomNodes = (Element) roomSpaceLayer.getElementsByTagName(Parameters.GML_NODES).item(0);
        // get edges
        Element roomEdges = (Element) roomSpaceLayer.getElementsByTagName(Parameters.GML_EDGES).item(0);
        // get stateMember and get informations
        NodeList roomStateMembers = roomNodes.getElementsByTagName(Parameters.GML_STMEMB);
        HashMap<String, MapGraph.State> stateMap = new HashMap<>();
        // define temp vars and get state's informations
        Element stateMemb, state, gmlGeom, gmlPoint;
        String id, label, tempStr;
        int coords[] = new int[2];
        for(int i=0; i<roomStateMembers.getLength(); i++)
        {
            stateMemb = (Element) roomStateMembers.item(i);
            state = (Element) stateMemb.getElementsByTagName(Parameters.GML_STATE).item(0);
            // get id
            id = state.getAttribute(Parameters.GML_ID);
            // get label
            label = state.getElementsByTagName(Parameters.GML_NAME).item(0).getFirstChild().getTextContent();
            // get coordinates
            gmlGeom = (Element) state.getElementsByTagName(Parameters.GML_GEOM).item(0);
            gmlPoint = (Element) gmlGeom.getElementsByTagName(Parameters.GML_POINT).item(0);
            tempStr = gmlPoint.getElementsByTagName(Parameters.GML_POS).item(0).getTextContent();
            coords[0] = Integer.parseInt(tempStr.split(" ")[0]);
            coords[1] = Integer.parseInt(tempStr.split(" ")[1]);
            // add state to the list
            stateMap.put(id, (new MapGraph.State(id, label, coords[0], coords[1])));
            System.out.println(id + "\t" + label + "\t" + coords[0] + "\t" + coords[1]);
        }
        // get TransitionMember and get start and end states
        NodeList roomTransitionMembers = roomEdges.getElementsByTagName(Parameters.GML_TRMEMB);
        List<MapGraph.Transition> transList = new ArrayList<>();
        // define temp vars and get transition's informations
        Element transMember, trans, start, end;
        MapGraph.State startState, endState;
        for(int i = 0; i<roomTransitionMembers.getLength(); i++)
        {
            transMember = (Element) roomTransitionMembers.item(i);
            trans = (Element) transMember.getElementsByTagName(Parameters.GML_TRANS).item(0);
            // get start State
            start = (Element) trans.getElementsByTagName(Parameters.GML_STARTNODE).item(0);
            tempStr = start.getAttribute(Parameters.GML_ATTR_XREF);
            startState = stateMap.get(tempStr.split("#")[1]);
            // get end State
            end = (Element) trans.getElementsByTagName(Parameters.GML_ENDNODE).item(0);
            tempStr = end.getAttribute(Parameters.GML_ATTR_XREF);
            endState = stateMap.get(tempStr.split("#")[1]);
            // add transition to list
            transList.add(new MapGraph.Transition(startState, endState));
        }
        // construct the Graph
        return new MapGraph(stateMap, transList);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Load gml file and create graphs
        Document document = null;
        try
        {
            InputStream xml = this.getAssets().open(Parameters.MAP_FILE);
            XMLParser parser = new XMLParser(xml);
            document = parser.getDocument();
        }
        catch(Exception e){}

        // Create room graph and sensor graph
        MapGraph roomGraph = createGraph(document, Parameters.ROOMS);
        MapGraph sensorGraph = createGraph(document, Parameters.SENSORS);

        // Create multilayer graph with room graph and sensor graph
        MultilayerMapGraph multigraph = new MultilayerMapGraph(roomGraph, sensorGraph);

        MapGraph.State room0 = roomGraph.getState("ST0");
        MapGraph.State room1 = roomGraph.getState("ST1");
        MapGraph.State room2 = roomGraph.getState("ST2");
        MapGraph.State sensor0 = sensorGraph.getState("SS0");
        MapGraph.State sensor1 = sensorGraph.getState("SS1");
        MapGraph.State sensor2 = sensorGraph.getState("SS2");
        MapGraph.State sensor3 = sensorGraph.getState("SS3");
        MapGraph.State sensor4 = sensorGraph.getState("SS4");
        MapGraph.State sensor5 = sensorGraph.getState("SS5");

        // Set interlayer connections (room <-> sensor)
        multigraph.addInterConnection(room0, sensor0);
        multigraph.addInterConnection(room0, sensor1);
        multigraph.addInterConnection(room0, sensor2);
        multigraph.addInterConnection(room2, sensor3);
        multigraph.addInterConnection(room1, sensor4);
        multigraph.addInterConnection(room1, sensor5);


        // Create a TileView object
        tileView = new XTileView(this);
        // Setup the TileView
        tileView.setSize(Parameters.TILE_WIDTH, Parameters.TILE_HEIGHT);
        for (int i = 0; i < Parameters.DETAIL_SCALES.length; i++)
            tileView.addDetailLevel(Parameters.DETAIL_SCALES[i], Parameters.TILES_PATH + Parameters.DETAIL_SCALES[i] * 100 + "/%col%_%row%" + Parameters.TILE_EXTENSION, Parameters.SAMPLE_IMAGE, Parameters.TILE_SIZE, Parameters.TILE_SIZE);

        // Define the bounds using the map size in pixel
        tileView.defineRelativeBounds(0, 0, Parameters.TILE_WIDTH, Parameters.TILE_HEIGHT);

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
        if (id == R.id.action_localize)
        {
            // add marker for my position
            if(myPosition == null)
            {
                myPosition = new ImageView(getApplicationContext());
                myPosition.setImageResource(R.drawable.blue_dot_xl);
                tileView.addZoomableMarker(myPosition, "blue_dot", 2100, 2000);
            }
            tileView.forceZoom(0.75);
            tileView.moveToMarker(myPosition, true);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void initialize(){
        Beacon beacon;

        beaconManager = new BeaconManager(this);

        // Check if device supports Bluetooth Low Energy.
        if (!beaconManager.hasBluetooth()) {
            Toast.makeText(this, getResources().getString(R.string.no_bluetooth), Toast.LENGTH_LONG).show();
            return;
        }

        // If Bluetooth is not enabled, let user enable it.
        if (!beaconManager.isBluetoothEnabled()) {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
        }

        beaconManager.setRangingListener(new BeaconManager.RangingListener() {
            @Override
            public void onBeaconsDiscovered(Region region, final List<Beacon> beacons) {
                String name = beacons.get(0).getName();
            }
        });
    }
}
