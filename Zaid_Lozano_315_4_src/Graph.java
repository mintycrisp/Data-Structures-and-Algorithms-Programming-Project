// Zaid Lozano
// CMSC 315 Project 4
// This class manages the graph data structure including vertices and edges.
// It provides methods to manipulate the graph and perform graph-related operations such as
// checking connectivity, detecting cycles, and performing searches.

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Graph {
    // Stores all vertices in the graph
    private List<Vertex> vertices;
    // Adjacency list to represent the connections between vertices
    private List<List<Integer>> adjacencyList;

    // Initializes the graph with empty lists for vertices and edges
    public Graph() {
        vertices = new ArrayList<>();
        adjacencyList = new ArrayList<>();
    }

    // Adds a vertex to the graph and initializes its adjacency list
    public void addVertex(Vertex v) {
        vertices.add(v);
        adjacencyList.add(new ArrayList<>());
    }

    // Adds an edge between two vertices identified by their indices in the vertex list
    public void addEdge(Vertex u, Vertex v) {
        int uIndex = vertices.indexOf(u);
        int vIndex = vertices.indexOf(v);
        if (uIndex == -1 || vIndex == -1) {
            // Throws an exception if either vertex is not found
            throw new IllegalArgumentException("One or both vertices do not exist.");
        }
        // Adds each vertex to the other's adjacency list
        adjacencyList.get(uIndex).add(vIndex);
        adjacencyList.get(vIndex).add(uIndex);
    }

    // Checks if all vertices in the graph are connected
    public boolean isConnected() {
        if (vertices.isEmpty()) return false;
        boolean[] visited = new boolean[vertices.size()];
        dfs(0, visited);
        // Check if all vertices were visited
        for (boolean visit : visited) {
            if (!visit) return false;
        }
        return true;
    }

    // Depth-first search helper method to check connectivity
    private void dfs(int v, boolean[] visited) {
        visited[v] = true;
        for (int neighbor : adjacencyList.get(v)) {
            if (!visited[neighbor]) {
                dfs(neighbor, visited);
            }
        }
    }

    // Checks for cycles in the graph
    public boolean hasCycles() {
        boolean[] visited = new boolean[vertices.size()];
        for (int i = 0; i < vertices.size(); i++) {
            if (!visited[i] && hasCyclesUtil(i, -1, visited)) {
                return true;
            }
        }
        return false;
    }

    // Helper method to detect cycles using DFS
    private boolean hasCyclesUtil(int current, int parent, boolean[] visited) {
        visited[current] = true;
        for (int neighbor : adjacencyList.get(current)) {
            if (!visited[neighbor]) {
                if (hasCyclesUtil(neighbor, current, visited)) return true;
            } else if (neighbor != parent) {
                // A cycle is detected if an adjacent vertex is visited and is not the parent
                return true;
            }
        }
        return false;
    }

    // Performs a depth-first search and returns the list of visited vertices
    public List<Vertex> depthFirstSearch() {
        List<Vertex> result = new ArrayList<>();
        if (vertices.isEmpty()) return result;
        boolean[] visited = new boolean[vertices.size()];
        dfsRecursive(0, visited, result);
        return result;
    }

    // Recursive DFS method to visit vertices
    private void dfsRecursive(int v, boolean[] visited, List<Vertex> result) {
        visited[v] = true;
        result.add(vertices.get(v));
        for (int neighbor : adjacencyList.get(v)) {
            if (!visited[neighbor]) {
                dfsRecursive(neighbor, visited, result);
            }
        }
    }

    // Performs a breadth-first search and returns the list of visited vertices
    public List<Vertex> breadthFirstSearch() {
        List<Vertex> result = new ArrayList<>();
        if (vertices.isEmpty()) return result;
        boolean[] visited = new boolean[vertices.size()];
        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);
        visited[0] = true;
        while (!queue.isEmpty()) {
            int v = queue.poll();
            result.add(vertices.get(v));
            for (int neighbor : adjacencyList.get(v)) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.add(neighbor);
                }
            }
        }
        return result;
    }

    public Vertex getVertex(String vertexName) {
        for (Vertex v : vertices) {
            if (v.getName().equals(vertexName)) {
                return v;
            }
        }
        return null; // Return null if the vertex wasn't found
    }
    
}
