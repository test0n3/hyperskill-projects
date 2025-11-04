import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

class WhiteNoise {
  public static void main(String[] args) {
    String filePath = "./hyperskill-dataset-117313126.txt";
    String inputData = ReadFile.returnContent(filePath);
    String[] inputData2;

    if (inputData != null) {
      inputData2 = inputData.split(",");
      Integer sum = Arrays.stream(inputData2)
          .map(Integer::parseInt)
          .reduce(0, (a, b) -> a + b);
      System.out.println("sum: " + sum);

      int residue = sum % 360;
      if (residue < 0) {
        System.out.println("resp: " + (360 - residue));
      } else {
        System.out.println("resp: " + residue);
      }
    } else {
      System.out.println("inputData is null");
    }

  }
}

class ReadFile {
  public static String returnContent(String filePath) {
    String content = "";

    try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
      while ((content = reader.readLine()) != null) {
        System.out.println("content: " + content);
        return content;
      }
    } catch (IOException e) {
      System.err.println("Error reading file: " + e.getMessage());
    }
    return content;
  }
}
