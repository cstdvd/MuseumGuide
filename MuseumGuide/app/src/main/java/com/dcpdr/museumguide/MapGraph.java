package com.dcpdr.museumguide;
import org.jgrapht.Graph;
import org.jgrapht.Graphs;

import java.util.Iterator;
import java.util.List;

public class MapGraph
{
    public class State
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

    public class Transition
    {
        public State start;
        public State end;

        public Transition(State start, State end)
        {
            this.start = start;
            this.end = end;
        }
    }

    private Graph<State, Transition> roomGraph;

    public  MapGraph(List<State> vertixList, List<Transition> edgeList)
    {
        State v;
        Transition e;

        for(Iterator<State> iterator=vertixList.iterator(); iterator.hasNext();)
        {
            v = iterator.next();
            roomGraph.addVertex(v);
        }
        for(Iterator<Transition> iterator=edgeList.iterator(); iterator.hasNext();)
        {
            e = iterator.next();
            roomGraph.addEdge(e.start, e.end);
        }
    }
}
