package com.dcpdr.museumguide;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.RemoteException;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import com.kontakt.sdk.android.configuration.BeaconActivityCheckConfiguration;
import com.kontakt.sdk.android.configuration.MonitorPeriod;
import com.kontakt.sdk.android.connection.OnServiceBoundListener;
import com.kontakt.sdk.android.data.RssiCalculators;
import com.kontakt.sdk.android.device.BeaconDevice;
import com.kontakt.sdk.android.device.Region;
import com.kontakt.sdk.android.manager.BeaconManager;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MapsActivity extends ActionBarActivity {

    private XTileView tileView;
    private ImageView myPosition;
    private String pathId;

    private BeaconManager beaconManager;
    private ArrayList<NavigableItem> pictures;

    public MultilayerMapGraph multigraph;
    public MapGraph.State myRoom, prevRoom = null, mySensor, prevSensor = null;

    public BeaconDevice myBeacon;

    // Takes GML document and graph's layer and returns the graph
    private MapGraph createGraph(Document document, int level) {
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
        for (int i = 0; i < roomStateMembers.getLength(); i++) {
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
        for (int i = 0; i < roomTransitionMembers.getLength(); i++) {
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

    // Takes the document and returns the list of the pictures
    private ArrayList<NavigableItem> getPictures(Document document)
    {
        NodeList nodes = document.getElementsByTagName("picture");

        String name, author, description, sensor, room;
        ArrayList<NavigableItem> pics = new ArrayList<>();

        for (int i = 0; i < nodes.getLength(); i++) {
            Element element = (Element) nodes.item(i);

            name = element.getElementsByTagName("name").item(0).getTextContent();
            author = element.getElementsByTagName("author").item(0).getTextContent();
            description = element.getElementsByTagName("description").item(0).getTextContent();
            sensor = element.getElementsByTagName("id").item(0).getTextContent();
            room = multigraph.getConnectedState(Parameters.SENSORS, sensor).id;

            NavigableItem picture = new NavigableItem(name, author, description, sensor, room);
            pics.add(picture);
        }

        return pics;
    }

    private void initialize()
    {
        beaconManager = BeaconManager.newInstance(this);
        beaconManager.setScanMode(BeaconManager.SCAN_MODE_LOW_POWER);
        beaconManager.setRssiCalculator(RssiCalculators.newLimitedMeanRssiCalculator(5));
        beaconManager.setBeaconActivityCheckConfiguration(BeaconActivityCheckConfiguration.DEFAULT);
        beaconManager.setDistanceSort(BeaconDevice.DistanceSort.ASC);
        beaconManager.setMonitorPeriod(MonitorPeriod.MINIMAL);
        beaconManager.registerMonitoringListener(new BeaconManager.MonitoringListener() {
            @Override
            public void onBeaconAppeared(final Region region, final BeaconDevice beacon) {
                MapsActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                    }
                });
            }

            @Override
            public void onBeaconsUpdated(final Region region, final List<BeaconDevice> beacons) {
                MapsActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (!beacons.isEmpty())
                            myBeacon = beacons.get(0);
                    }
                });
            }

            @Override
            public void onMonitorStart() {
                MapsActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                    }
                });
            }

            @Override
            public void onMonitorStop() {
                MapsActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(myBeacon != null)
                            getRoom(myBeacon.getUniqueId());
                    }
                });
            }

            @Override
            public void onRegionEntered(final Region region) {
                MapsActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                    }
                });
            }

            @Override
            public void onRegionAbandoned(final Region region) {
                MapsActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                    }
                });
            }
        });
    }

    // Find a path from actual location to the selected room and draw it
    private void navigation(NavigableItem item)
    {
        // check if there is already a path drawn and, if it is the case, the latter is removed
        if(tileView.getPathsDrawn() > 0)
            tileView.removeNavigablePath(pathId);

        MapGraph.State destination = multigraph.getState(Parameters.SENSORS, item.getSensorId());
        if(mySensor.id.equals(destination.id)) {
            List<MapGraph.State> path = multigraph.getPath(Parameters.SENSORS, mySensor.id, destination.id);
            List<double[]> positions = new ArrayList<>();
            for (MapGraph.State s : path) {
                s = changeCoords(s);
                positions.add(new double[]{s.coords[0], s.coords[1]});
            }
            positions.remove(0);

            pathId = mySensor.id + "-" + destination.id;
            tileView.drawNavigablePath(pathId, new XTileView.NavigablePath(positions));
        }
    }

    // check if you've reached the destination
    private void checkForDestination(double x, double y)
    {
        if(tileView.getPathsDrawn() > 0) {
            // get destination coordinates
            XTileView.NavigablePath navP = tileView.getNavigablePath(pathId);
            double[] dest = navP.points.get(navP.points.size() - 1);

            if ((x == dest[0]) && (y == dest[1])) {
                tileView.removeNavigablePath(pathId);
                Toast.makeText(getApplicationContext(), getString(R.string.destination),
                        Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myPosition = null;

        // Load gml file and create graphs
        Document document = null;
        try {
            InputStream xml = this.getAssets().open(Parameters.MAP_FILE);
            XMLParser parser = new XMLParser(xml);
            document = parser.getDocument();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(),e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        // Create room graph and sensor graph
        MapGraph roomGraph = createGraph(document, Parameters.ROOMS);
        MapGraph sensorGraph = createGraph(document, Parameters.SENSORS);

        // Create multilayer graph with room graph and sensor graph
        multigraph = new MultilayerMapGraph(roomGraph, sensorGraph);

        MapGraph.State room0 = roomGraph.getState("ST0");
        MapGraph.State room1 = roomGraph.getState("ST1");
        MapGraph.State room2 = roomGraph.getState("ST2");
        MapGraph.State room3 = roomGraph.getState("ST3");
        MapGraph.State room4 = roomGraph.getState("ST4");
        MapGraph.State room6 = roomGraph.getState("ST6");
        MapGraph.State room7 = roomGraph.getState("ST7");
        MapGraph.State sensor0 = sensorGraph.getState("SS0");
        MapGraph.State sensor1 = sensorGraph.getState("SS1");
        MapGraph.State sensor2 = sensorGraph.getState("SS2");
        MapGraph.State sensor3 = sensorGraph.getState("SS3");
        MapGraph.State sensor4 = sensorGraph.getState("SS4");
        MapGraph.State sensor5 = sensorGraph.getState("SS5");
        MapGraph.State sensor6 = sensorGraph.getState("SS6");
        MapGraph.State sensor7 = sensorGraph.getState("SS7");
        MapGraph.State sensor8 = sensorGraph.getState("SS8");
        MapGraph.State sensor9 = sensorGraph.getState("SS9");

        // Set interlayer connections (room <-> sensor)
        multigraph.addInterConnection(room0, sensor0);
        multigraph.addInterConnection(room0, sensor1);
        multigraph.addInterConnection(room0, sensor2);
        multigraph.addInterConnection(room2, sensor3);
        multigraph.addInterConnection(room1, sensor4);
        multigraph.addInterConnection(room1, sensor5);
        multigraph.addInterConnection(room6, sensor6);
        multigraph.addInterConnection(room7, sensor7);
        multigraph.addInterConnection(room4, sensor8);
        multigraph.addInterConnection(room3, sensor9);

        // Create navigable items
        Document doc = null;
        try {
            InputStream xml = this.getAssets().open(Parameters.ITEM_FILE);
            XMLParser parser = new XMLParser(xml);
            doc = parser.getDocument();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(),e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        pictures = getPictures(doc);

        // Create a TileView object
        tileView = new XTileView(this);
        // Setup the TileView
        tileView.setSize(Parameters.TILE_WIDTH, Parameters.TILE_HEIGHT);
        for (int i = 0; i < Parameters.DETAIL_SCALES.length; i++)
            tileView.addDetailLevel(Parameters.DETAIL_SCALES[i],
                    Parameters.TILES_PATH + Parameters.DETAIL_SCALES[i] * 100 + "/%col%_%row%" + Parameters.TILE_EXTENSION,
                    Parameters.SAMPLE_IMAGE, Parameters.TILE_SIZE, Parameters.TILE_SIZE);

        // Define the bounds using the map size in pixel
        tileView.defineRelativeBounds(0, 0, Parameters.TILE_WIDTH, Parameters.TILE_HEIGHT);

        // Display the TileView
        tileView.setScale(0);
        setContentView(tileView);

        // Initialize Kontakt
        initialize();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_maps, menu);
        return true;
    }

    @Override
    protected void onStart(){
        super.onStart();

        if(!beaconManager.isBluetoothEnabled()){
            final Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(intent, Parameters.REQUEST_ENABLE_BT);
        } else if(beaconManager.isConnected()){
            startMonitoring();
        } else {
            connect();
        }
    }

    @Override
    protected void onStop(){
        super.onStop();
        if(beaconManager.isConnected()){
            beaconManager.stopMonitoring();
        }
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        beaconManager.disconnect();
        beaconManager = null;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == Parameters.REQUEST_ENABLE_BT) {
            if(resultCode != Activity.RESULT_OK){
                Toast.makeText(getApplicationContext(),getString(R.string.bluetooth_not_enable), Toast.LENGTH_SHORT).show();
            }
        }else if(requestCode == Parameters.REQUEST_SEARCH) {
            // Check for selected item in Search Activity
            // If there is, show navigation from actual location to the selected item
            ArrayList<NavigableItem> selected = data.getParcelableArrayListExtra("Search");
            navigation(selected.get(0));
        }
    }

    private void startMonitoring(){
        try{
            beaconManager.startMonitoring();
        }catch (RemoteException e){
            Toast.makeText(getApplicationContext(),e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void connect() {
        try{
            beaconManager.connect(new OnServiceBoundListener() {
                @Override
                public void onServiceBound() throws RemoteException {
                    beaconManager.startMonitoring();
                }
            });
        } catch (RemoteException e){
            Toast.makeText(getApplicationContext(),e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_localize) {
            // add marker for my position
            if (myPosition != null) {
                tileView.forceZoom(0.75);
                tileView.moveToMarker(myPosition, true);
            }
            return true;
        }else if (id == R.id.action_search){
            if(myRoom == null)
                return true;
            Intent nextActivityIntent = new Intent(this, SearchActivity.class);
            // put the list of pictures
            nextActivityIntent.putParcelableArrayListExtra("Pictures", pictures);
            startActivityForResult(nextActivityIntent, Parameters.REQUEST_SEARCH);
        }else if (id == R.id.action_info)
        {
            if(!checkForPicture())
                Toast.makeText(getApplicationContext(), getString(R.string.no_pictures), Toast.LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected(item);
    }

    // Check if there is a picture in the actual location
    private boolean checkForPicture(){
        NavigableItem pic = null;
        for(NavigableItem n : pictures){
            if(n.getSensorId().equals(mySensor.id)) {
                pic = n;
                break;
            }
        }
        if(pic != null) {
            InfoDialog dialog = new InfoDialog(this, pic);
            dialog.show();
            return true;
        } else
            return false;
    }

    // Return sensor id <-> beacon id
    private String getSensorId(String id)
    {
        return Parameters.sensorIdMap.get(id);
    }

    // Image has origin coordinates in Top Left,
    // map has origin coordinates in Bottom Left, so y must be changed
    private MapGraph.State changeCoords(MapGraph.State state)
    {
        MapGraph.State ret = new MapGraph.State(state);
        ret.coords[1] = Parameters.TILE_HEIGHT - ret.coords[1];
        return ret;
    }

    private void getRoom(String id)
    {
        String sensorId = getSensorId(id);

        myRoom = multigraph.getConnectedState(Parameters.SENSORS, sensorId);
        mySensor = multigraph.getState(Parameters.SENSORS, sensorId);
        mySensor = changeCoords(mySensor);

        if((prevSensor == null) || (!prevSensor.id.equals(mySensor.id))){
            checkForPicture();
            prevSensor = mySensor;
        }

       if((prevRoom == null) || (!prevRoom.label.equals(myRoom.label))){
            Toast.makeText(getApplicationContext(), myRoom.label, Toast.LENGTH_SHORT).show();
            prevRoom = myRoom;
        }

        // Add position dot
        if (myPosition == null) {
            myPosition = new ImageView(getApplicationContext());
            myPosition.setImageResource(R.drawable.blue_dot_7);
            tileView.addZoomableMarker(myPosition, "blue_dot", mySensor.coords[0], mySensor.coords[1]);
            tileView.forceZoom(0.75);
            tileView.moveToMarker(myPosition, true);
        }else {
            tileView.moveMarker("blue_dot", mySensor.coords[0], mySensor.coords[1]);
            checkForDestination(mySensor.coords[0], mySensor.coords[1]);
        }
    }
}