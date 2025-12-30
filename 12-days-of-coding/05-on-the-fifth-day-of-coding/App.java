class App {
  public static void main(String[] args) {
    String filePath = "./hyperskill-dataset-119073947.txt";
    String content = ReadFile.readContent(filePath);

    double[][] inputArray = convertStringToDoubleArray(content);
    // display inputArray
    System.out.println("x - y");
    for (double[] line : inputArray) {
      System.out.println(line[0] + ", " + line[1]);
    }

    // create shoelaces
    double firstShoelace = 0.0;
    double secondShoelace = 0.0;
    for (int i = 0; i < inputArray.length - 1; i++) {
      firstShoelace += inputArray[i][0] * inputArray[i+1][1];
      secondShoelace += inputArray[i][1] * inputArray[i+1][0];
    }
    System.out.println("firstShoelace: " + firstShoelace + ", secondShoelace: " + secondShoelace);
    double finalResult = 0.5 * Math.abs(firstShoelace - secondShoelace);
    System.out.println("finalResult: " + finalResult);
    System.out.printf("%.2f", finalResult);
  }

  private static double[][] convertStringToDoubleArray(String data) {
    String[] lines = data.split("\n");

    double[][] dataArray = new double[lines.length + 1][];

    for (int i = 0; i < lines.length; i++) {
      String[] values = lines[i].split(",");
      dataArray[i] = new double[values.length];

      for (int j = 0; j < values.length; j++) {
        try {
          double doubleValue = Double.parseDouble(values[j]);
          dataArray[i][j] = doubleValue;
        } catch (NumberFormatException e) {
          System.err.println("Error parsing number: " + values[j] + " - defaulting to 0");
          dataArray[i][j] = 0;
        }
      }
    }
    // add last elements
    dataArray[lines.length] = new double[2];
    dataArray[lines.length] = dataArray[0];
    return dataArray;
  }
}
