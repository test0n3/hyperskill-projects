class App {
  public static void main(String[] args) {
    String filePath = "./hyperskill-dataset-117461383.txt";
    String content = ReadFile.readContent(filePath);
    System.out.println("Content: " + content.trim());
    long convertedContent = Long.parseLong(content.trim());
    final long OVERFLOW = (long) (Math.pow(2, 32));
    System.out.println("OVERFLOW: " + OVERFLOW);
    long overflowValue = 0l;
    long overflowAccum = 0l;

    do {
      overflowValue = (long) convertedContent / OVERFLOW;
      overflowAccum += overflowValue;
      System.out.println("overflowValue: " + overflowValue + ", overflowAccum: " + overflowAccum +
          ", convertedContent: " + convertedContent);
      convertedContent = convertedContent - OVERFLOW;
    } while (overflowValue > 1);

    String binary = Long.toBinaryString(convertedContent);
    System.out.println("convertedContent: " + convertedContent);
    System.out.println(overflowAccum + "," + binary);
  }
}
