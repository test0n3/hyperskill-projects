package com.gerbosan;

import java.util.Map;
import java.util.Scanner;
import java.util.List;

public class App {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    System.out.print("Input file address: ");
    String filePathTest = "./" + input.nextLine();

    // String filePath = "./hyperskill-dataset-117267365.txt";
    String data = ReadFile.returnContent(filePathTest);

    if (!data.isEmpty()) {
      Map<Character, Long> result = NumberUtils.inputCounter(data);
      System.out.println("HashMap of frequency: " + result);
      // get HashMap of frequency :
      // {0=106, 1=92, 2=95, 3=110, 4=97, 5=107, 6=95, 7=94, 8=112, 9=92}

      List<Character> topFour = NumberUtils.topFourDigits(result);
      System.out.println("Four most relevant numbers: " + topFour);
      // get list of the four most frequent numbers:
      // [8, 3, 5, 0, 4, 2, 6, 7, 1, 9]

    } else {
      System.out.println("data empty");
    }
    input.close();
  }
}
