package xyz.jameskr.fm.graph;

import java.util.ArrayList;

/**
 * Unique vertex of graph
 *
 * @author James 
 * @date 11/19/16
 */
public class Vertex {

    /**
     * List of vertices which this vertex point towards
     */
    private ArrayList<Vertex> directed;

    /**
     * Label of this Vertex
     */
    private String label;

    public Vertex(String label) {
        this.label = label;
        this.directed = new ArrayList<>();
    }

    /**
     * Add a vertex which this vertex points towards
     *
     * @param x vertex to add
     * @return true if vertex was added.
     */
    public boolean addDirectedNeighbor(Vertex x) {
        if (directed.contains(x))
            return false;
        directed.add(x);
        return true;
    }

    /**
     * @return Name of this label
     */
    public String getName() {
        return label;
    }

    /**
     * @return The directed array list
     */
    public ArrayList<Vertex> getDirected() {
        return directed;
    }

}
