package com.dcpdr.museumguide;

import org.jgrapht.Graphs;
import org.jgrapht.alg.DijkstraShortestPath;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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

    public List<String> getPath(int layer, String startId, String endId)
    {
        MapGraph.State startState = mapGraphs[layer].getState(startId);
        MapGraph.State endState = mapGraphs[layer].getState(endId);


        DijkstraShortestPath DSPath = new DijkstraShortestPath(mapGraphs[layer].getGraph(), startState, endState);
        List<MapGraph.State> stateList = Graphs.getPathVertexList(DSPath.getPath());

        List<String> stringList = new ArrayList<>();
        for(MapGraph.State s : stateList)
            stringList.add(s.id);

        return stringList;
    }

    public MapGraph getGraph(int layer)
    {
        return mapGraphs[layer];
    }

    public MapGraph.State getState(int layer, String id)
    {
        return mapGraphs[layer].getState(id);
    }

    public Set<MapGraph.State> getAllStates(int layer)
    {
        return mapGraphs[layer].getAllStates();
    }

    // Returns connected state from state's id and its layer
    public MapGraph.State getConnectedState(int layer, String id)
    {
        MapGraph.State state = mapGraphs[layer].getState(id);

        for(MultilayerMapGraph.InterLayerConnection i : connections)
        {
            if(layer == Parameters.SENSORS)
                if(i.end.id.equals(state.id))
                    return i.start;
            else if(layer == Parameters.ROOMS)
                if(i.start.id.equals(state.id))
                    return i.end;
        }
        return null;
    }
}
