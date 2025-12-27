import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

class App {
  public static void main(String[] args) {
    String filePath = "./hyperskill-dataset-118979765.txt";
    String content = ReadFile.readContent(filePath);

    String[] textsArray = content.split("\n");
    Map<String, Integer> errorCountingAllDay = new HashMap<>();
    Map<String, Integer> errorsAtThreePM = new HashMap<>();

    for (String item : textsArray) {
      String[] temp = item.split(" ");
      String time = temp[0];
      String error = temp[1];

      errorCountingAllDay.merge(error, 1, Integer::sum);

      if (time.matches("15:(([0-2][0-9])|(30))")) {
        errorsAtThreePM.merge(error, 1, Integer::sum);
      }

      // System.out.println("time: " + temp[0] + " error: " + temp[1]);
    }

    String mostCommonDailyError = mostCommonError(errorCountingAllDay);
    if (!mostCommonDailyError.isBlank()) {
      errorsAtThreePM.remove(mostCommonDailyError);
      String mostCommonErrorAtThree = mostCommonError(errorsAtThreePM);
      System.out.println(mostCommonErrorAtThree);
    } else {
      System.out.println("Empty log file");
    }
  }

  private static String mostCommonError(Map<String, Integer> errorsList) {
    Optional<Map.Entry<String, Integer>> results = errorsList.entrySet()
        .stream()
        .max(Map.Entry
            .comparingByValue());
    if (results.isPresent()) {
      return results.get().getKey();
    } else {
      return "";
    }
  }
}
