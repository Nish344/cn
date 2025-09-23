package computerNetworking;

import java.util.Scanner;

/* Bellman Ford Algorithm
   Single source shortest path algorithm
   Handles negative edge weights (unlike Dijkstra's).
   Time Complexity: O(V * E)
*/

public class BellmanFord {
    private int[] D;
    private int numVer;
    private static final int MAX_VALUE = 999;

    public BellmanFord(int numVer) {
        this.numVer = numVer;
        D = new int[numVer];
    }

    public void bfEvaluation(int src, int A[][]) {
        // Step 1: Initialize distances
        for (int i = 0; i < numVer; i++) {
            D[i] = MAX_VALUE;
        }
        D[src] = 0;

        // Step 2: Relax edges repeatedly (V-1 times)
        for (int i = 1; i <= numVer - 1; i++) {
            for (int sn = 0; sn < numVer; sn++) {
                for (int dn = 0; dn < numVer; dn++) {
                    if (A[sn][dn] != MAX_VALUE) {
                        if (D[sn] != MAX_VALUE && D[dn] > D[sn] + A[sn][dn]) {
                            D[dn] = D[sn] + A[sn][dn];
                        }
                    }
                }
            }
        }

        // Step 3: Check for negative cycles
        for (int sn = 0; sn < numVer; sn++) {
            for (int dn = 0; dn < numVer; dn++) {
                if (A[sn][dn] != MAX_VALUE) {
                    if (D[sn] != MAX_VALUE && D[dn] > D[sn] + A[sn][dn]) {
                        System.out.println("The graph contains a negative cycle!");
                        return;
                    }
                }
            }
        }

        // Step 4: Print results
        for (int vertex = 0; vertex < numVer; vertex++) {
            if (D[vertex] == MAX_VALUE) {
                System.out.println("Distance from source " + src + " to " + vertex + " is Infinity");
            } else {
                System.out.println("Distance from source " + src + " to " + vertex + " is " + D[vertex]);
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the number of vertices:");
        int numVer = scanner.nextInt();

        int[][] A = new int[numVer][numVer];

        System.out.println("Enter the adjacency matrix (0 for no edge):");
        for (int sn = 0; sn < numVer; sn++) {
            for (int dn = 0; dn < numVer; dn++) {
                A[sn][dn] = scanner.nextInt();
                if (sn == dn) {
                    A[sn][dn] = 0;
                } else if (A[sn][dn] == 0) {
                    A[sn][dn] = MAX_VALUE;
                }
            }
        }

        System.out.println("Enter the source vertex (0-based index):");
        int source = scanner.nextInt();

        BellmanFord ford = new BellmanFord(numVer);
        ford.bfEvaluation(source, A);

        scanner.close();
    }
}
