import java.util.Arrays;

class App {
  public static void main(String[] args) {
    String filePath = "./hyperskill-dataset-119120692.txt";
    String content = ReadFile.readContent(filePath);

    String[] inputData = content.split("\\R");

    String[][] chessBoard = createChessBoard(inputData);
    printChessBoard(chessBoard);

    int[][] queenCoordinates = queensPosition(content);
    System.out.println("x - y");
    for (int[] row : queenCoordinates) {
      System.out.println(row[0] + " , " + row[1]);
    }

    System.out.println("Conflicts: " + findConflicts(queenCoordinates));
  }

  private static int[][] queensPosition(String input) {
    // String[] lines = input.split("\\R");
    // int[][] queenCoordinates = new int[lines.length][];
    //
    // for (int i = 0; i < lines.length; i++) {
    //   int[] values = Arrays.stream(lines[i].split(",\\s*"))
    //   .mapToInt(Integer::parseInt)
    //   .toArray();
    //   queenCoordinates[i] = values;
    // }
    // return queenCoordinates;
    return Arrays.stream(input.split("\\R"))
                 .map(line -> Arrays.stream(line.split(",\\s*"))
                                    .mapToInt(Integer::parseInt)
                                    .toArray())
                 .toArray(int[][]::new);
  }

  private static int findConflicts(int[][] coordinates) {
    int conflicts = 0;

    for (int i = 0; i < coordinates.length-1; i++) {
      for (int j = i+1; j < coordinates.length; j++) {
        int x1 = coordinates[i][0], y1 = coordinates[i][1];
        int x2 = coordinates[j][0], y2 = coordinates[j][1];
        if (x1 == x2 || y1 == y2 || Math.abs(x1 - x2) == Math.abs(y1 - y2)) {
          conflicts++;
        }
      }
    }
    return conflicts; 
  }

  private static String[][] createChessBoard(String[] inputData) {
    String[][] chessBoard = new String[8][8];
    for (String[] row : chessBoard) {
      Arrays.fill(row, "_");
    }
    for (String input : inputData) {
      String[] position = input.split(",");
      int x = Integer.parseInt(position[0]);
      int y = Integer.parseInt(position[1]);
      chessBoard[x][y] = "Q";
    }
    return chessBoard;
  }

  private static void printChessBoard(String[][] chessBoard) {
    for (String[] line : chessBoard) {
      Arrays.stream(line).forEach(elem -> System.out.print(elem + " "));
      System.out.println();
    }
  } 
}
