import java.util.Arrays;
import java.util.List;
import java.util.Map;

class App {
  public static void main(String[] args) {
    String filePath = "./hyperskill-dataset-117461486.txt";
    String content = ReadFile.readContent(filePath);
    // System.out.println("Content: " + content);
    String[] namesList = content.split(" ");
    // System.out.println("namesList: " + Arrays.toString(namesList));
    String[] orderedNamesList = Arrays.stream(namesList)
        .map(value -> StringUtils.sortChars(value))
        .toArray(String[]::new);

    // System.out.println("orderedNamesList: " + Arrays.toString(orderedNamesList));
    Map<String, Long> counter = StringUtils.stringCounter(orderedNamesList);
    List<String> unique = StringUtils.uniqueNames(counter);
    System.out.println(unique);
    String recoveredName = StringUtils.recoverString(namesList, unique.get(0));
    System.out.println(recoveredName);
  }
}
