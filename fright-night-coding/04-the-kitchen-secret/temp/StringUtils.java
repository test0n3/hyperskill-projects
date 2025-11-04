class StringUtils {
  public static String[][] inputToArray(String input) {
    if (input != null) {
      String[] firstStep = input.split("\\n");
      String[][] finalStep = new String[firstStep.length][];

      for (int i = 0; i < firstStep.length; i++) {
        finalStep[i] = firstStep[i].split(",");
      }
      return finalStep;
    } else {
      System.out.println("input null");
      return null;
    }
  }
}
