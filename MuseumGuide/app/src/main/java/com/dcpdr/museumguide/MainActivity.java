package com.dcpdr.museumguide;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


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

    public MapGraph createGraph(Document document, int level)
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
        HashMap<String, MapGraph.State> stateMap = new HashMap<String, MapGraph.State>();
        // define temp vars and get state's informations
        Element stateMemb, state, gmlGeom, gmlPoint;
        String id = "", label = "", tempStr;
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
        List<MapGraph.Transition> transList = new ArrayList<MapGraph.Transition>();
        // define temp vars and get transition's informations
        Element transMember, trans, start, end;
        MapGraph.State startState = null, endState = null;
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
        MapGraph mg = new MapGraph(stateMap, transList);

        return mg;
    }

    @Override
    public void onClick(View v)
    {
        //Intent nextActivityIntent = new Intent(this, MapsActivity.class);
        //startActivity(nextActivityIntent);


        Document document = null;
        try
        {
            InputStream xml = this.getAssets().open(Parameters.MAP_FILE);
            XMLParser parser = new XMLParser(xml);
            document = parser.getDocument();
        }
        catch(Exception e){}

        MapGraph roomGraph = createGraph(document, Parameters.ROOMS);
        MapGraph sensorGraph = createGraph(document, Parameters.SENSORS);

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

        String s = "";
        s += "\n" + multigraph.addInterConnection(room0, sensor0);
        s += "\n" + multigraph.addInterConnection(room0, sensor1);
        s += "\n" + multigraph.addInterConnection(room0, sensor2);
        s += "\n" + multigraph.addInterConnection(room2, sensor3);
        s += "\n" + multigraph.addInterConnection(room1, sensor4);
        s += "\n" + multigraph.addInterConnection(room1, sensor5);

        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }
}
