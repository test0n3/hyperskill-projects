import java.util.regex.Pattern;

class Piano {
  public static String allNoteSearch(String input) {
    boolean isFound = false;
    int windowSize = 7;
    int pos = 0;
    String window = "";
    String result = "";
    while (isFound != true) {
      window = input.substring(pos, windowSize + pos);
      System.out.println("substring: " + window + ", pos: " + pos + " windowSize: " + windowSize);
      if (window.matches("(?=.*A)(?=.*B)(?=.*C)(?=.*D)(?=.*E)(?=.*F)(?=.*G).*")) {
        isFound = true;
        result = window;
      } else {
        pos = pos + 1;
      }

      if (pos == input.length() - windowSize) {
        pos = 0;
        windowSize = windowSize + 1;
      }

      if (windowSize == input.length()) {
        isFound = true;
      }
    }
    return result;
  }
}
