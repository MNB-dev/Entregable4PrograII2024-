
public class Main {
    public static void main(String[] args) {
        //Ej2
        Graph graph = new Graph();
        graph.addVertex(0);
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);

        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 3);
        graph.addEdge(2, 3);

        Set<Graph> subgraphs = GraphUtils.getConnectedSubgraphs(graph);
        System.out.println("Connected subgraphs: " + subgraphs.size());

        boolean isForest = GraphUtils.isForest(graph);
        System.out.println("Is forest: " + isForest);

        //Ej3
        int[][] adjacencyMatrix = {
                {0, 1, 1, 0},
                {1, 0, 0, 1},
                {1, 0, 0, 1},
                {0, 1, 1, 0}
        };

        boolean result = GraphUtils.isTree(adjacencyMatrix);
        System.out.println("Is tree: " + result);

        int[][] adjacencyMatrixNotTree = {
                // 0  1  2  3  4
                {0, 1, 1, 1, 0}, // 0
                {1, 0, 1, 0, 1}, // 1
                {1, 1, 0, 0, 1}, // 2
                {1, 0, 0, 0, 0}, // 3
                {0, 1, 1, 0, 0}  // 4
        };

        result = GraphUtils.isTree(adjacencyMatrixNotTree);
        System.out.println("Is tree: " + result);
    }
}