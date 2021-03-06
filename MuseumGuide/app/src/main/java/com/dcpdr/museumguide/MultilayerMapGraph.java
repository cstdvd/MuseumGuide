package com.dcpdr.museumguide;

import org.jgrapht.Graphs;
import org.jgrapht.alg.DijkstraShortestPath;

import java.util.ArrayList;
import java.util.List;
// All the graphs are contained inside a multilayer graph
// In our application, the multilayer graph contains two layer: room graph and sensor graph
// In order to know which sensors are contained by a room, the two layer are connected by
// inter layer connections
// Inter layer connections, as the graphs, the states and the transition, are all defined in the
// indoorGML standard
public class MultilayerMapGraph
{
	// InterLayerConnection includes only two states of separate layers
	// it is implied that start state belongs to layer1 and end state belongs to layer2
	public static class InterLayerConnection
	{
		public MapGraph.State start, end;

		public InterLayerConnection(MapGraph.State s, MapGraph.State e)
		{
			this.start = s;
			this.end = e;
		}
	}
	
	private MapGraph[] mapGraphs;
	List<InterLayerConnection> connections;
	
	public MultilayerMapGraph(MapGraph layer1, MapGraph layer2)
	{
		mapGraphs = new MapGraph[2];
		connections = new ArrayList<>();
		
		mapGraphs[0] = layer1;
		mapGraphs[1] = layer2;
	}
	
	public void addInterConnection(MapGraph.State state1, MapGraph.State state2)
	{
		InterLayerConnection connection;
		
		// states must be present in both the graphs
		// (state1 in layer1 and state2 in layer2)
		if(mapGraphs[0].contains(state1) && mapGraphs[1].contains(state2))
			connection = new InterLayerConnection(state1, state2);
		else
			return;
		
		connections.add(connection);
	}

    // Navigation is implemented by the use of the Dijkstra's algorithm to find the path between
    // the selected states
    public List<MapGraph.State> getPath(int layer, String startId, String endId)
    {
        MapGraph.State startState = mapGraphs[layer].getState(startId);
        MapGraph.State endState = mapGraphs[layer].getState(endId);


        DijkstraShortestPath DSPath = new DijkstraShortestPath(mapGraphs[layer].getGraph(), startState, endState);

        return Graphs.getPathVertexList(DSPath.getPath());
    }

    public MapGraph getGraph(int layer)
    {
        return mapGraphs[layer];
    }

    public MapGraph.State getState(int layer, String id)
    {
        return mapGraphs[layer].getState(id);
    }

    // Returns connected state from state's id and its layer
    // It is used to know which sensors are placed inside a room
    public MapGraph.State getConnectedState(int layer, String id)
    {
		MapGraph.State state = mapGraphs[layer].getState(id);

        for(InterLayerConnection i : connections)
        {
            if(layer == Parameters.SENSORS)
                if(i.end.id.equals(state.id))
                    return i.start;
        }
        return null;
    }
}
