import java.util.*;

class GraphUtils {

    // Función que recibe un grafo G y devuelve un Set<Graph> con G1,G2, ..., Gn
    public static Set<Graph> getConnectedSubgraphs(Graph graph) {
        Set<Graph> connectedSubgraphs = new Set<>();
        Set visited = new Set();

        Set vertices = graph.getVertices();
        while (!vertices.isEmpty()) {
            int vertex = (int) vertices.choose();
            if (!visited.contains(vertex)) {
                Graph subgraph = new Graph();
                explore(graph, vertex, visited, subgraph);
                connectedSubgraphs.add(subgraph);
            }
            vertices.remove(vertex);
        }

        return connectedSubgraphs;
    }

    private static void explore(Graph graph, int vertex, Set visited, Graph subgraph) {
        Stack<Integer> stack = new Stack<>();
        stack.push(vertex);

        while (!stack.isEmpty()) {
            int current = stack.pop();
            if (!visited.contains(current)) {
                visited.add(current);
                subgraph.addVertex(current);
                for (int neighbor : graph.getAdjVertices(current)) {
                    subgraph.addVertex(neighbor);
                    subgraph.addEdge(current, neighbor);
                    if (!visited.contains(neighbor)) {
                        stack.push(neighbor);
                    }
                }
            }
        }
    }

    // Función que recibe G y devuelve true si G es un bosque
    public static boolean isForest(Graph graph) {
        Set<Graph> connectedSubgraphs = getConnectedSubgraphs(graph);

        while (!connectedSubgraphs.isEmpty()) {
            Graph subgraph = connectedSubgraphs.choose();
            if (!isTree(subgraph)) {
                return false;
            }
        }
        return true;
    }

    // Función auxiliar que verifica si un grafo es un árbol
    private static boolean isTree(Graph graph) {
        Set visited = new Set();
        int startVertex = (int) graph.getVertices().choose();

        // Verificar si el grafo es conexo y no tiene ciclos
        if (hasCycle(graph, startVertex, visited, -1)) {
            return false;
        }

        // Verificar que todos los nodos hayan sido visitados (el grafo es conexo)
        return visited.size() == graph.getVertices().size();
    }

    private static boolean hasCycle(Graph graph, int vertex, Set visited, int parent) {
        visited.add(vertex);

        for (int neighbor : graph.getAdjVertices(vertex)) {
            if (!visited.contains(neighbor)) {
                if (hasCycle(graph, neighbor, visited, vertex)) {
                    return true;
                }
            } else if (neighbor != parent) {
                return true;
            }
        }

        return false;
    }

    public static boolean isTree(int[][] adjacencyMatrix) {
        int n = adjacencyMatrix.length;

        // Comprobar si el grafo es conexo
        boolean[] visited = new boolean[n];
        dfs(0, adjacencyMatrix, visited);

        for (boolean v : visited) {
            if (!v) {
                return false; // El grafo no es conexo
            }
        }

        // Comprobar si contiene un K3
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (adjacencyMatrix[i][j] == 1) {
                    for (int k = j + 1; k < n; k++) {
                        if (adjacencyMatrix[i][k] == 1 && adjacencyMatrix[j][k] == 1) {
                            return false; // Contiene un K3
                        }
                    }
                }
            }
        }

        return true; // Es un árbol
    }

    private static void dfs(int vertex, int[][] adjacencyMatrix, boolean[] visited) {
        visited[vertex] = true;
        for (int i = 0; i < adjacencyMatrix.length; i++) {
            if (adjacencyMatrix[vertex][i] == 1 && !visited[i]) {
                dfs(i, adjacencyMatrix, visited);
            }
        }
    }
}