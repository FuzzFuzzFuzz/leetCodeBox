class Solution {
    public int minCost(int n, int[][] edges) {

        // Build adjacency list with both forward and reverse edges
        // Forward edge u->v costs w (normal traversal)
        // Reverse edge v->u costs 2*w (using node v's switch)
        // Dijkstra visiting each node once naturally enforces the "one switch use per node" rule
        ArrayList<int[]>[] adjList = new ArrayList[n];

        for (int i = 0; i < n; i++) {

            adjList[i] = new ArrayList<>();
        }
            

        for (int[] edge : edges) {

            int fromNode = edge[0];
            int toNode   = edge[1];
            int edgeCost = edge[2];

            // Normal forward edge
            adjList[fromNode].add(new int[]{toNode, edgeCost});

            // Reverse edge representing switch activation at toNode
            adjList[toNode].add(new int[]{fromNode, 2 * edgeCost});
        }

        // Initialize distances to infinity, except source node 0
        final int INF = 1_000_000_000;
        int[] distTo = new int[n];
        Arrays.fill(distTo, INF);
        distTo[0] = 0;

        // Min-heap ordered by cost: [costToReach, node]
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        pq.add(new int[]{0, 0});

        while (!pq.isEmpty()) {

            int[] currentEntry = pq.poll();
            int currentCost = currentEntry[0];
            int currentNode = currentEntry[1];

            // First time we reach destination is guaranteed minimum cost
            if (currentNode == n - 1) {
                return currentCost;
            }

            // Skip stale entries where a cheaper path was already found
            if (currentCost != distTo[currentNode]) {
                continue;
            }

            // Relax all outgoing (and pre-added reverse) edges
            for (int[] neighborEntry : adjList[currentNode]) {

                int neighborNode = neighborEntry[0];
                int edgeCost = neighborEntry[1];

                int newCost = distTo[currentNode] + edgeCost;

                if (newCost < distTo[neighborNode]) {

                    distTo[neighborNode] = newCost;
                    
                    pq.add(new int[]{distTo[neighborNode], neighborNode});
                }
            }
        }

        // Destination unreachable
        return -1;
    }
}
