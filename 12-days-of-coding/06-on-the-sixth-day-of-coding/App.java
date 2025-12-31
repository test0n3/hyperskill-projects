
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Set;
import java.util.HashSet;

class App {

  public static void main(String[] args) {
    String filePath = "./hyperskill-dataset-119100448.txt";
    String content = ReadFile.readContent(filePath);
    String[] parts = content.split("\\R", 2);

    String start = parts[0];
    String temporalRelationsList = (parts.length > 1) ? parts[1] : "";

    String[][] inputArray = convertStringToArray(temporalRelationsList);
       
    System.out.println("start: " + start);
    System.out.println("node1 - node2");
    for (String[] line : inputArray) {
      System.out.println(line[0] + ", " + line[1]);
    }

    Map<String, List<String>> graph = generateInputGraph(inputArray);
    System.out.println("graph: " + graph);

    String furthestNode = breathFirst(graph, start);
    System.out.println("FurthestNode: " + furthestNode);
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
    }
    return result;
  }

  private static String breathFirst(Map<String, List<String>> graph, String start) {
    Queue<String> queue = new LinkedList<>();
    Set<String> visited = new HashSet<>();
    Map<String, Integer> distance = new HashMap<>();

    queue.add(start);
    visited.add(start);
    
    String furthestNode = start;
    distance.put(start,0);

    while(!queue.isEmpty()) {
      furthestNode = queue.poll();

      for (String neighbor : graph.getOrDefault(furthestNode, new ArrayList<>())) {
        if (!visited.contains(neighbor)) {
          visited.add(neighbor);
          queue.add(neighbor);
          distance.put(neighbor, distance.get(furthestNode) + 1);
        }
      }
    }
    // TODO check distance, get keys with the max values and list them alphabetically
    System.out.println("distance: " + distance);
    return furthestNode;
  }
}

distance: {Storm=2, Parka=2, Frostbite=2, Shiver=3, Ravine=3, Moon=1, 
Toboggan=3, Rime=3, Ginger=2, Tempest=3, Summit=3, Cedar=3, 
Powder=3, Aurora=2, Narwhal=3, Dawn=1, Snowshoe=3, Flurry=1, Cliff=1, 
Glacier=1, Scarf=2, Cinder=2, Cardinal=2, Snowpack=2, Alpine=3, 
Mittens=3, Compass=3, Breeze=2, Comet=2, Valley=2, Willow=3, 
Ember=2, Seal=1, Chickadee=1, East=3, Ridge=1, Raven=3, Birch=3, 
Vanilla=2, Caribou=2, Snowbank=3, Aspen=3, Slope=3, Star=2, 
Holly=1, Wolf=3, Luge=1, Owl=3, South=1, Whiteout=3, Nova=1, 
Ice=2, Snowdrift=3, Walrus=3, Sweater=2, Blaze=2, Boots=2, 
Drift=3, Icicle=2, Arctic=2, Fleece=3, Tundra=1, Blizzard=2, 
Sleet=3, Nutmeg=2, Snow=2, Juniper=2, Penguin=2, Twilight=3, 
Permafrost=3, Cocoa=2, Moose=2, Hawk=2, Winter=3, Hearth=2, 
North=1, Cinnamon=1, Marshmallow=3, Orbit=3, Frost=3, Bobsled=2, 
Fox=3, Solstice=2, Pine=1, Canyon=1, Hoar=2, Sleigh=3, Crystal=3, 
Bear=3, Knit=1, Ivy=2, Gale=2, Clove=2, Flake=1, Skate=2, Peak=3, 
Hail=1, Spruce=1, Fireside=3, Chill=3, West=3, Dusk=2, Mocha=2, 
Fir=3, Ski=3, Equinox=2, Reindeer=0, Wool=1, Midnight=1, 
Sparrow=2, Evergreen=3, Sled=1}
