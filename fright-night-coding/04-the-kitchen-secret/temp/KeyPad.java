import java.util.Arrays;

class KeyPad {
  private final static char[][] KEYPAD = new char[][] {
      { 'A', 'B', 'C', 'D' },
      { 'E', 'F', 'G', 'H' },
      { 'I', 'J', 'K', 'L' },
      { 'M', 'N', 'O', 'P' } };
  private final static int[][] INITIALPOS = { { 0 }, { 0 } };
  private static int[][] pos = INITIALPOS;

  public static void printKeyPad() {
    for (char[] line : KEYPAD) {
      for (char c : line) {
        System.out.print(" " + c);
      }
      System.out.println();
    }
  }

  public static void printKeyPadString(String[][] instructions) {
    StringBuilder result = new StringBuilder();
    System.out.println("Instructions length: " + instructions.length);
    for (int i = 0; i < instructions.length; i++) {
      System.out.println("\n\ninstructions[" + i + "] length: " + instructions[i].length);
      System.out.println("instructions[" + i + "]: " + Arrays.toString(instructions[i]));
      for (int j = 0; j < instructions[i].length; j++) {
        System.out.print("\ninstruction " + j + ", initial pos: " + pos[0][0] + ", " + pos[1][0]);
        updatePos(instructions[i][j]);
        System.out.print(" instruction: " + instructions[i][j] + ", pos: " + pos[0][0] + ", " + pos[1][0]);
      }
      System.out.println("\nchar pos: " + pos[0][0] + ", " + pos[1][0] + " char: " + KEYPAD[pos[0][0]][pos[1][0]]);
      result.append(KEYPAD[pos[0][0]][pos[1][0]]);
      pos[0][0] = 0;
      pos[1][0] = 0;
      System.out.println("char initial pos: " + pos[0][0] + ", " + pos[1][0]);
    }

    System.out.println("result: " + result.append('A'));
    System.out.println("result size: " + result.length());
  }

  private static void updatePos(String instruction) {
    int vertical = pos[0][0];
    int horizontal = pos[1][0];

    switch (instruction) {
      case "UP":
        vertical = vertical - 1;
        break;
      case "DOWN":
        vertical = vertical + 1;
        break;
      case "LEFT":
        horizontal = horizontal - 1;
        break;
      case "RIGHT":
        horizontal = horizontal + 1;
        break;
      default:
        System.out.println("ERROR");
    }

    if ((vertical >= 0 && vertical <= 3) && (horizontal >= 0 && horizontal <= 3)) {
      pos[0][0] = vertical;
      pos[1][0] = horizontal;
    }
  }
}
