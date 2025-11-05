package com.gerbosan;

import java.util.Scanner;

public class App {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    System.out.println("Input file address: ");
    String filePath = "./" + input.nextLine();
    String inputData = ReadFile.returnContent(filePath);
    if (!inputData.isEmpty()) {
      System.out.println("degress: " + WhiteNoise.returnDegrees(inputData));
    } else {
      System.out.println("empty data.");
    }
    input.close();
  }
}
