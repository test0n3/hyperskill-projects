import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;
import java.util.stream.Collectors;

class App {
  public static void main(String[] args) {
    String filePath = "./hyperskill-dataset-119117224.txt";
    String content = ReadFile.readContent(filePath);

    String[][] inputArray = convertStringToArray(content);
    Map<String, List<String>> graph = generateInputGraph(inputArray);
    Set<String> nodes = new HashSet<>();
    nodes = graph.keySet();
    System.out.println("graph: " + graph + "\n graph size: " + graph.size());
    System.out.println("nodes: " + nodes + "\n nodes size: " + nodes.size());
    System.out.println(isEurelianPath(graph));
  }

  private static String[][] convertStringToArray(String data) {
    String[] lines = data.split("\\R");
    String[][] dataArray = new String[lines.length][];

    for (int i = 0; i < lines.length; i++) {
      String[] values = lines[i].split(",");
      dataArray[i] = new String[values.length];

      dataArray[i] = values;
    }
    return dataArray;
  }

  private static Map<String, List<String>> generateInputGraph(String[][] input) {
    Map<String, List<String>> result = new HashMap<>();
    for (String[] line : input) {
      result.computeIfAbsent(line[0], k -> new ArrayList<>()).add(line[1]);
      result.computeIfAbsent(line[1], k -> new ArrayList<>()).add(line[0]);
    }
    return result;
  }

  private static boolean isEurelianPath(Map<String, List<String>> inputGraph) {
    Map<String, Integer> nodeDegrees = inputGraph.entrySet()
    .stream()
    .collect(Collectors.toMap(Map.Entry::getKey, 
      entry -> entry.getValue()
      .size()));
    System.out.println("nodeDegress: " + nodeDegrees);

    Map<String, Integer> filtered = nodeDegrees.entrySet().stream()
    .filter(entry -> entry.getValue() % 2 != 0)
    .collect(Collectors.toMap(Map.Entry::getKey,
    Map.Entry::getValue));
    System.out.println("filtered: " + filtered + ", filtered size(): " + filtered.size());
    if (filtered.size() == 0 || filtered.size() == 2) {
      System.out.println("It is Eurelian path");
      return true;
    }
    System.out.println("required bridges to complete Eurelian Path: " + (filtered.size() / 2 - 1));
    return false;
  }
}
