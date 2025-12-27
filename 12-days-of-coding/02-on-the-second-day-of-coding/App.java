import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Collections;
import java.util.stream.Stream;

class App {
  public static void main(String[] args) {
    String filePath = "./hyperskill-dataset-119005607.txt";
    String content = ReadFile.readContent(filePath);
    int target = Integer.valueOf(content.split("\n")[0]);
    System.out.println("target: " + target);
    int[] options = Arrays.stream(content.split("\n")[1].split(","))
        .mapToInt(Integer::parseInt)
        .toArray();
    for (int option : options) {
      System.out.println(option);
    }
    // System.out.println("options.length: " + options.length);

    int right = options.length - 1;
    int left = 0;
    // List<Integer> averages = new ArrayList<>();

    int bestSum = options[0] + options[options.length - 1];
    // averages.add(bestSum);
    while (left < right) {
      int currentSum = options[left] + options[right];
      if (Math.abs((target*2)- currentSum) < Math.abs((target*2)- bestSum)) {
        bestSum = currentSum;
      }
      // averages.add(currentSum);

      if (currentSum < (target * 2)) {
        left++;
      } else {
        right--;
      }
    }
    // averages.forEach(System.out::println);
    // System.out.println("max average: " + Collections.max(averages) + " length: " + averages.size());
    System.out.println("bestSum average: " + (int) Math.ceil(bestSum / 2.0));
  }
}
