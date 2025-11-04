import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class StringUtils {
  public static String sortChars(String input) {
    char[] temporal = input.toCharArray();
    Arrays.sort(temporal);
    return new String(temporal);
  }

  public static Map<String, Long> stringCounter(String[] input) {
    Map<String, Long> counter = new HashMap<>();
    for (String name : input) {
      if (counter.containsKey(sortChars(name))) {
        counter.put(name, counter.get(name) + 1);
      } else {
        counter.put(name, 1L);
      }
    }
    return counter;
  }

  public static List<String> uniqueNames(Map<String, Long> input) {
    List<String> uniqueName = input.entrySet()
        .stream()
        .filter(key -> key.getValue() == 1)
        .map(Map.Entry::getKey)
        .collect(Collectors.toList());
    return uniqueName;
  }

  public static String recoverString(String[] input, String key) {
    String result = "";
    for (String name : input) {
      if (key.equals(sortChars(name))) {
        result = name;
        break;
      }
    }
    return result;
  }
}
