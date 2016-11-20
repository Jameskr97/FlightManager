package xyz.jameskr.fm.graph;

import java.util.ArrayList;

/**
 * Vertex (node) of graph.
 *
 * @author James 
 * @date 11/19/16
 */
public class Vertex {

    private ArrayList<Vertex> directed;
    private String label;
    private boolean visited;

    public Vertex(String label) {
        this.label = label;
        this.directed = new ArrayList<>();
        this.visited = false;
    }

    public boolean addDirectedNeighbor(Vertex x) {
        if (directed.contains(x))
            return false;
        directed.add(x);
        return true;
    }

    public String getName() {
        return label;
    }

    public Vertex getAdjacent(int index){
        return directed.get(index);
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public ArrayList<Vertex> getDirected() {
        return directed;
    }

}
