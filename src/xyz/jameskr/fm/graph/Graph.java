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

    private HashMap<String, Vertex> vertices;

    public Graph() {
        vertices = new HashMap<>();
    }

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

    private void addVertex(Vertex v) {
        vertices.put(v.getName(), v);
    }

    public boolean hasVertex(String label) {
        return vertices.containsKey(label);
    }

    public Vertex getVertex(String label) {
        if (vertices.containsKey(label))
            return vertices.get(label);
        return null;
    }

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

    public void depthFirstSearch(String origin, String dest, List<List<String>> paths, LinkedHashSet<String> currentPath) {
        Vertex v = vertices.get(origin);
        currentPath.add(v.getName());

        if (origin.equalsIgnoreCase(dest) || v.getDirected().isEmpty()) {
            paths.add(new ArrayList<>(currentPath));
            currentPath.remove(v.getName());
            return;
        }

        for (Vertex x : v.getDirected()) {
            depthFirstSearch(x.getName(), dest, paths, currentPath);
        }

        currentPath.remove(v.getName());
    }

    public void printGraph() {
        for (String s : vertices.keySet()) {
            Vertex x = vertices.get(s);
            System.out.print(x.getName() + " -> ");
            ArrayList<Vertex> con = x.getDirected();
            for (Vertex v : con)
                System.out.print(v.getName() + "");
            System.out.println();
        }
    }


}
