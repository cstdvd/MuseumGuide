package com.dcpdr.museumguide;
import org.jgrapht.Graphs;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import java.util.HashMap;
import java.util.List;

public class MapGraph
{
    public static class State
    {
        public String id;
        public String label;
        public int[] coords;

        public State(String id, String label, int x, int y)
        {
            this.id = id;
            this.label = label;
            this.coords = new int[2];
            this.coords[0] = x;
            this.coords[1] = y;
        }
    }
    
    public static class Transition
    {
    	public State start, end;
    	
    	public Transition(State start, State end)
    	{
    		this.start = start;
    		this.end = end;
    	}
    }


    private  UndirectedGraph<State, DefaultEdge> roomGraph;

    public  MapGraph(List<State> vertixList, List<Transition> transList)
    {
    	roomGraph = new SimpleGraph<State, DefaultEdge>(DefaultEdge.class);
    	
    	// add all vertices
        for(State s : vertixList)
        	roomGraph.addVertex(s);
        
        // add all transitions
        for(Transition t : transList)
        	roomGraph.addEdge(t.start, t.end);
        
    }
    
    public  MapGraph(HashMap<String, State> vertixList, List<Transition> transList)
    {
    	roomGraph = new SimpleGraph<State, DefaultEdge>(DefaultEdge.class);
    	
    	// add all vertices
        for(State s : vertixList.values())
        	roomGraph.addVertex(s);
        
        // add all transitions
        for(Transition t : transList)
        	roomGraph.addEdge(t.start, t.end);
        
    }
    
    
    public String getAllNeighbours(State vertix)
    {
    	String ret = "";
    	List<State> tmp =  Graphs.neighborListOf(roomGraph, vertix);
    	for(State s: tmp)
    		ret += s.label + " ";
    	
    	return ret;
    }
}
