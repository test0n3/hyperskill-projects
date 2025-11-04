import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.Map;
import java.util.List;

class UniqueLetters {
  public static void main(String[] args) {
    String filePath = "./hyperskill-dataset-117341390.txt";
    String inputData = ReadFile.returnContent(filePath);
    if (inputData != null) {
      Map<Character, Long> countedChars = CharUtils.charCounter(inputData);
      System.out.println("countedChars: " + countedChars);
      List<Character> filteredChars = CharUtils.uniqueCharacters(countedChars);
      System.out.println("filteredChars: " + filteredChars);
      String uniqueString = CharUtils.uniqueString(filteredChars, inputData);
      System.out.println("uniqueString: " + uniqueString);
    } else {
      System.out.println("empty file");
    }
  }
}

class ReadFile {
  public static String returnContent(String filePath) {
    try {
      String content = Files.readString(Paths.get(filePath));
      System.out.println("File read successfully. Total length: " + content.length());
      return content;
    } catch (IOException e) {
      System.err.println("Error reading file: " + e.getMessage());
      return null;
    }
  }
}

class CharUtils {
  public static Map<Character, Long> charCounter(String input) {
    Map<Character, Long> counter = input.chars()
        .mapToObj(c -> (char) c)
        .collect(Collectors.groupingBy(
            Function.identity(),
            Collectors.counting()));
    return counter;
  }

  public static List<Character> uniqueCharacters(Map<Character, Long> input) {
    List<Character> uniqueChar = input.entrySet()
        .stream()
        .filter(key -> key.getValue() == 1)
        .map(Map.Entry::getKey)
        .collect(Collectors.toList());
    return uniqueChar;
  }

  public static String uniqueString(List<Character> uniqueCharacters, String input) {
    char[] inputChar = input.toCharArray();
    StringBuilder result = new StringBuilder();
    for (char c : inputChar) {
      if (uniqueCharacters.contains(c)) {
        result.append(c);
      }
    }
    return result.toString();
  }
}
