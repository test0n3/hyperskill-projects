import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

class App {
  public static void main(String[] args) {
    String filePath = "./hyperskill-dataset-119170589.txt";
    String content = ReadFile.readContent(filePath);

    int[][] districtGrid = createDistrictGrid(content);
    // print grid
    for (int[] row : districtGrid) {
      for (int cell : row) {
        System.out.print(cell + " ");
      }
      System.out.println();
    }

    Map<Node, List<Edge>> adjList = GridToGraph.buildAdjacencyList(districtGrid);
    List<Edge> edges = adjList.get(new Node(0, 0));

    for (Edge e : edges) {
      System.out.println("From (0,0) to (" + e.target.r + ", " + e.target.c + ") weight: " + e.weight);
    }

    System.out.println(dijkstra(adjList, new Node(0, 0), new Node(19, 19)));
  }

  private static int[][] createDistrictGrid(String rawData) {
    return Arrays.stream(rawData.split("\\R"))
        .map(line -> Arrays.stream(line.split(",\\s*"))
            .mapToInt(Integer::parseInt)
            .toArray())
        .toArray(int[][]::new);
  }

  private static int dijkstra(Map<Node, List<Edge>> adjList, Node start, Node end) {
    // Initialize distances to Infinity
    Map<Node, Integer> dist = new HashMap<>();
    for (Node n : adjList.keySet()) {
      dist.put(n, Integer.MAX_VALUE);
    }

    // Set source distance to 0 and add to Priority Queue
    dist.put(start, 0);
    PriorityQueue<NodeDist> pq = new PriorityQueue<>();
    pq.add(new NodeDist(start, 0));

    while (!pq.isEmpty()) {
      NodeDist current = pq.poll();
      Node u = current.node;
      // Optimization: If we reached target, we can return early
      if (u.equals(end))
        return dist.get(u);

      // Skip outdated entries in the PQ
      if (current.distance > dist.get(u))
        continue;

      // Relax edges
      for (Edge edge : adjList.getOrDefault(u, new ArrayList<>())) {
        Node v = edge.target;
        int weight = edge.weight;

        int newDist = dist.get(u) + weight;
        if (newDist < dist.get(v)) {
          dist.put(v, newDist);
          pq.add(new NodeDist(v, newDist));
        }
      }
    }
    return dist.getOrDefault(end, -1);
  }

  static class NodeDist implements Comparable<NodeDist> {
    Node node;
    int distance;

    NodeDist(Node node, int distance) {
      this.node = node;
      this.distance = distance;
    }

    @Override
    public int compareTo(NodeDist other) {
      return Integer.compare(this.distance, other.distance);
    }
  }
}
