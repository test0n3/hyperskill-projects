import java.io.IOException;
import java.nio.file.Paths;
import java.nio.file.Files;

class KitckenKeypad {
  private final static char[][] KEYPAD = new char[][] {
      { 'A', 'B', 'C', 'D' },
      { 'E', 'F', 'G', 'H' },
      { 'I', 'J', 'K', 'L' },
      { 'M', 'N', 'O', 'P' } };
  private final static int[][] INITIALPOS = { { 0 }, { 0 } };
  private static int[][] pos = INITIALPOS;

  public static void main(String[] args) {
    for (char[] line : KEYPAD) {
      System.out.println(line);
    }

    String filePath = "./hyperskill-dataset-117380127.txt";
    String content = ReadFile.readContent(filePath);

    String[][] instructions = StringUtils.inputToArray(content);
    StringBuilder result = new StringBuilder();

    for (int i = 0; i < instructions.length; i++) {
      for (int j = 0; j < instructions[i].length; j++) {
        updatePos(instructions[i][j]);
      }
      result.append(KEYPAD[pos[0][0]][pos[1][0]]);
      pos = INITIALPOS;
    }

    System.out.println("result: " + result.append('A'));
    System.out.println("result size: " + result.length());
  }

  public static void updatePos(String instruction) {
    int vertical = pos[0][0];
    int horizontal = pos[1][0];

    if (instruction.equals("UP")) {
      vertical -= 1;
    }
    if (instruction.equals("DOWN")) {
      vertical += 1;
    }
    if (instruction.equals("LEFT")) {
      horizontal -= 1;
    }
    if (instruction.equals("RIGHT")) {
      horizontal += 1;
    }

    if ((vertical >= 0 && vertical <= 3) && (horizontal >= 0 && horizontal <= 3)) {
      pos[0][0] = vertical;
      pos[1][0] = horizontal;
    }
  }
}

class ReadFile {
  public static String readContent(String filePath) {
    try {
      String content = Files.readString(Paths.get(filePath));
      System.out.println("File read successfully. Total length: " + content.length());
      return content;
    } catch (IOException e) {
      System.err.println("Error reading file: " + e.getMessage());
      return null;
    }
  }
}

class StringUtils {
  public static String[][] inputToArray(String input) {
    String[] firstStep = input.split("\\n");
    String[][] finalStep = new String[firstStep.length][];

    for (int i = 0; i < firstStep.length; i++) {
      finalStep[i] = firstStep[i].split(",");
    }
    return finalStep;
  }
}
