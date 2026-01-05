class App {
  public static void main(String[] args) {
    String filePath = "./hyperskill-dataset-119214819.txt";
    String content = ReadFile.readContent(filePath);

    String[] drummers = content.split("\\R");
    String model = drummers[0];
    String longestCommon = "";

    for (int i = 0; i < model.length(); i++) {
      for (int j = i + 1; j < model.length(); j++) {
        String candidate = model.substring(i, j);

        // clean up: avoid matching a single trailing comma
        if (candidate.startsWith(",") || candidate.endsWith(",")) continue;

        boolean matchAll = true;
        for (String drummer : drummers) {
          if (!drummer.contains(candidate)) {
            matchAll = false;
            break;
          }
        }

        if (matchAll && candidate.length() > longestCommon.length()) {
          longestCommon = candidate;
        }
      }
    }
    System.out.println("Longest Common Sequence: " + longestCommon + ", length: " + longestCommon.replaceAll(",", "").length());
  }
}
