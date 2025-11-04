class App {
  public static void main(String[] args) {
    String filePath = "./hyperskill-dataset-117516978.txt";
    String content = ReadFile.readContent(filePath);
    String[] textsArray = content.split("\n");
    StringBuilder result = new StringBuilder();

    for (String text : textsArray) {
      String temporal = text;
      String first = text.substring(0, 8);
      String second = text.substring(8, 16);
      for (int i = 0; i < first.length(); i++) {
        char charToErase = first.charAt(i);
        if (second.contains(String.valueOf(charToErase))) {
          temporal = temporal.replace(String.valueOf(first.charAt(i)), "");
        }
      }
      result.append(temporal);
    }
    System.out.println(result.toString());
  }
}
