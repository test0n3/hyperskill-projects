import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class GridToGraph {
  public static Map<Node, List<Edge>> buildAdjacencyList(int[][] grid) {
    Map<Node, List<Edge>> adjList = new HashMap<>();
    int rows = grid.length;
    int cols = grid[0].length;
    // Directions for moving: down, up, right, left
    int[][] directions = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

    for (int r = 0; r < rows; r++) {
      for (int c = 0; c < cols; c++) {
        Node current = new Node(r, c);
        adjList.putIfAbsent(current, new ArrayList<>());

        for (int[] d : directions) {
          int nr = r + d[0];
          int nc = c + d[1];
          // Check bounds
          if (nr >= 0 && nr < rows && nc >= 0 && nc < cols) {
            // The weight is the value of the Target cell
            int weight = grid[nr][nc];
            adjList.get(current).add(new Edge(new Node(nr, nc), weight));
          }
        }
      }
    }
    return adjList;
  }
}
