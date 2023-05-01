import java.util.*;

public class edmundkarp {

    private static final int INF = Integer.MAX_VALUE; // Initialize infinite flow value

    public static int maxFlow(int[][] capacity, int source, int sink) {
        int n = capacity.length; // Number of vertices in the graph
        int[][] residualCapacity = new int[n][n]; // Residual capacity of the edges
        int[] parent = new int[n]; // Parent array to store the path found by BFS
        int[] bottleneck = new int[n]; // Bottleneck capacity of the path found by BFS

        // Initialize residual capacity
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                residualCapacity[i][j] = capacity[i][j];
            }
        }

        int maxFlow = 0; // Initialize max flow

        while (true) {
            //Find Augmenting Path
            Arrays.fill(parent, -1);
            Arrays.fill(bottleneck, 0);
            Queue<Integer> queue = new LinkedList<>();
            queue.offer(source);
            parent[source] = -2;
            bottleneck[source] = INF;

            while (!queue.isEmpty()) {
                int u = queue.poll();
                for (int v = 0; v < n; v++) {
                    if (parent[v] == -1 && residualCapacity[u][v] > 0) {
                        parent[v] = u;
                        bottleneck[v] = Math.min(bottleneck[u], residualCapacity[u][v]);
                        if (v != sink) {
                            queue.offer(v);
                        } else {
                            // Found a path from source to sink
                            maxFlow += bottleneck[sink];
                            // Update residual capacity along the path
                            int p = sink;
                            while (p != source) {
                                int u1 = parent[p];
                                residualCapacity[u1][p] -= bottleneck[sink];
                                residualCapacity[p][u1] += bottleneck[sink];
                                p = u1;
                            }
                            break;
                        }
                    }
                }
            }

            if (parent[sink] == -1) {  // No augmenting path available
                System.out.println("As no more Augmenting Path is available: ");
                break;
            }
            System.out.println("The Max Flow till now is: "+maxFlow);
        }

        return maxFlow;
    }

    public static void main(String[] args) {
        System.out.println("I am Aryan Kumar 21BCT0275 and this is my code for Edmund Karp Algorithm");
        System.out.println("My graph is on my Phone Number 7003145004");
        int[][] capacity = { { 0, 7, 0, 0, 3, 1 },
                { 4, 0, 5, 0, 0, 4 },
                { 7, 0, 0, 0, 3, 1 },
                { 4, 5, 0, 0, 0, 4 },
                { 7, 0, 0, 3, 0, 1 },
                { 4, 5, 0, 0, 4, 0 } };
        int source = 0;
        int sink = 5;
        int maxFlow = maxFlow(capacity, source, sink);
        System.out.println("Final Max flow: " + maxFlow);
    }
}
