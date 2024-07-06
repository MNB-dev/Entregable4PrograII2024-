import java.util.*;

public class Graph {
    private final HashTable adjacencyList;

    public Graph() {
        this.adjacencyList = new HashTable();
    }

    public void addVertex(int vertex) {
        if (adjacencyList.get(vertex) == null) {
            adjacencyList.put(vertex, new int[0]);
        }
    }

    public void addEdge(int vertex1, int vertex2) {
        addVertex(vertex1);
        addVertex(vertex2);
        addNeighbor(vertex1, vertex2);
        addNeighbor(vertex2, vertex1);
    }

    private void addNeighbor(int vertex, int neighbor) {
        int[] neighbors = adjacencyList.get(vertex);
        int[] newNeighbors = Arrays.copyOf(neighbors, neighbors.length + 1);
        newNeighbors[newNeighbors.length - 1] = neighbor;
        adjacencyList.put(vertex, newNeighbors);
    }

    public int[] getAdjVertices(int vertex) {
        return adjacencyList.get(vertex);
    }

    public Set getVertices() {
        Set vertices = new Set();
        for (int i = 0; i < adjacencyList.size(); i++) {
            if (adjacencyList.get(i) != null) {
                vertices.add(i);
            }
        }
        return vertices;
    }
}