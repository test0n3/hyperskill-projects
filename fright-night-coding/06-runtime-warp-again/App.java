import java.util.Arrays;

class App {
  public static void main(String[] args) {
    String filePath = "./hyperskill-dataset-117430282.txt";
    String content = ReadFile.readContent(filePath);
    System.out.println("Content: " + content);
    String[] contentArray = content.split("\\n");
    System.out.println("contentArray: " + Arrays.toString(contentArray));
    int[][] intContent = Arrays.stream(contentArray)
        .map(value -> NumberUtils.parseVector(value))
        .toArray(int[][]::new);

    for (int[] line : intContent) {
      System.out.println(Arrays.toString(line));
    }

    long totalDistance = EuclideanDistance.euclideanDistance(intContent);
    System.out.println("Total distance: " + totalDistance);
  }
}
