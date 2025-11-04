import java.util.Arrays;

class NumberUtils {
  public static int[] parseVector(String input) {
    int[] vector = Arrays.stream(input.split(","))
        .mapToInt(value -> (int) Math.ceil(Double.parseDouble(value)))
        .toArray();
    return vector;
  }
}
