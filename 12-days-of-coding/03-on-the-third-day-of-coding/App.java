import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

class App {
  public static void main(String[] args) {
    String filePath = "./hyperskill-dataset-119025371.txt";
    String content = ReadFile.readContent(filePath);
    String[] passwords = content.split("\n");

    double bestScore = 0.0;
    String bestPass = "";

    for (String password : passwords) {
      String pass = password;
      double score = pass.length() * 1.0;
      System.out.println(pass + " initial score is: " + score);

      if (!hasLowerCase(pass)) {
        score *= 0.75;
        System.out.println(pass + " has no lower case: " + score);
      }
      if (!hasUpperCase(pass)) {
        score *= 0.75;
        System.out.println(pass + " has no upper case " + score);
      }
      if (!hasNumber(pass)) {
        score *= 0.75;
        System.out.println(pass + " has no numbers " + score);
      }
      if (!hasSpecialSymbol(pass)) {
        score *= 0.75;
        System.out.println(pass + " has no special symbol " + score);
      }

      score -= hasOccurrences(pass);

      System.out.println(pass + " has score: " + score + "\n");
      if (score > bestScore) {
        bestScore = score;
        bestPass = pass;
      }
    }
    System.out.println("\n\nBest password is: " + bestPass + " with score: " + bestScore);
  }

  private static boolean hasLowerCase(String pass) {
    return pass.matches(".*[a-z].*");
  }

  private static boolean hasUpperCase(String pass) {
    return pass.matches(".*[A-Z].*");
  }

  private static boolean hasNumber(String pass) {
    return pass.matches(".*[0-9].*");
  }

  private static boolean hasSpecialSymbol(String pass) {
    return pass.matches(".*[!@#$%^&*].*");
  }

  private static int hasOccurrences(String pass) {
    int passLength = pass.length();
    Map<String, Long> occurrences = Arrays.stream(pass.split(""))
        .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

    long maxCount = occurrences.values()
        .stream()
        .max(Long::compare)
        .orElse(0L);

    if (30.0 <= (maxCount * 100.0) / passLength) {
      return (int) maxCount;
    }
    return 0;
  }
}
