package xyz.jameskr.fm.graph;

import java.util.*;

/**
 * Graph data model, with ability to search for all paths between two vertices, assuming graph is
 * a directed acyclic graph.
 *
 * @author James 
 * @date 11/19/16
 */
public class Graph {

    /**
     * Graph map. Links are store in vertex as this is a DIRECTED graph.
     */
    private HashMap<String, Vertex> vertices;

    /**
     * Single purpose constructor. Init graph map.
     */
    public Graph() {
        vertices = new HashMap<>();
    }

    /**
     * Add a directed connection between two vertices and creates Vertex if it does not exist.
     *
     * @param from String of from vertex
     * @param to   String of to vertex
     */
    public void addDirectedConnection(String from, String to) {
        if (!vertices.containsKey(from) && !vertices.containsKey(to)) { // If neither vertex exists
            Vertex fromV = new Vertex(from), toV = new Vertex(to);
            fromV.addDirectedNeighbor(toV);
            addVertex(fromV);
            addVertex(toV);
        } else if (!vertices.containsKey(from) && vertices.containsKey(to)) { // If from vertex doesn't exist
            Vertex fromV = new Vertex(from);
            fromV.addDirectedNeighbor(vertices.get(to));
            addVertex(fromV);
        } else if (vertices.containsKey(from) && !vertices.containsKey(to)) { // If to vertex doesn't exist
            Vertex toV = new Vertex(to);
            vertices.get(from).addDirectedNeighbor(toV);
            addVertex(toV);
        } else { // If both vertices exist...
            vertices.get(from).addDirectedNeighbor(vertices.get(to));
        }
    }

    /**
     * Add vertex to the graph
     *
     * @param v Vertex to add to graph
     */
    private void addVertex(Vertex v) {
        vertices.put(v.getName(), v);
    }

    /**
     * Get flights from origin to destination
     *
     * @param origin      Origin airport code
     * @param destination Destination airport code
     * @return A List of a List of Strings, in which the inner list is a flight path. Returns null if failed.
     */
    public List<List<String>> getAllPaths(String origin, String destination) {
        if (origin.equalsIgnoreCase(destination)) {
            System.out.println("Origin can not equal destination.");
            return null;
        }

        if (!vertices.containsKey(origin)) {
            System.out.println("Origin does not exist");
            return null;
        }

        if (!vertices.containsKey(destination)) {
            System.out.println("Destination does not exist");
            return null;
        }

        // Get Paths
        List<List<String>> paths = new ArrayList<>();
        depthFirstSearch(origin, destination, paths, new LinkedHashSet<>());

        List<List<String>> validPaths = new ArrayList<>();
        for (List<String> p : paths) {
            if (p.get(0).equalsIgnoreCase(origin) && p.get(p.size() - 1).equalsIgnoreCase(destination))
                validPaths.add(p);
        }

        return validPaths;
    }

    /**
     * Depth First Search to traverse the graph and add paths to the list of paths.
     *
     * @param origin      Origin (or current) vertex label
     * @param dest        Vertex label to search for paths to
     * @param paths       A list of all valid paths
     * @param currentPath Current path the recursive function is going down
     */
    public void depthFirstSearch(String origin, String dest, List<List<String>> paths, LinkedHashSet<String> currentPath) {
        Vertex v = vertices.get(origin);
        currentPath.add(v.getName());

        if (origin.equalsIgnoreCase(dest) || v.getDirected().isEmpty()) {
            paths.add(new ArrayList<>(currentPath));
            currentPath.remove(v.getName());
            return;
        }

        for (Vertex x : v.getDirected())
            depthFirstSearch(x.getName(), dest, paths, currentPath);

        currentPath.remove(v.getName());
    }

}
