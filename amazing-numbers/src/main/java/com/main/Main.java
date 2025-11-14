package com.main;

import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner userInput = new Scanner(System.in);
    String input;

    System.out.println("Welcome to Amazing Numbers!");
    printInstructions();

    do {
      System.out.print("\nEnter a request: ");
      input = userInput.nextLine();

      if (input.isEmpty()) {
        printInstructions();
        continue;
      }

      String[] inputs = input.split(" ");

      if (NumberUtils.checkInputs(inputs) == 0) {
        if (!NumberUtils.anyZeros(inputs)) {
          Numbers number = new Numbers(inputs);
          number.execute();
        }
      }
    } while (!input.equals("0"));
    System.out.println("\nGoodbye!");

    userInput.close();
  }

  private static void printInstructions() {
    String instructions = """

        Supported requests:
        - enter a natural number to know its properties;
        - enter two natural numbers to obtain the properties of the list:
          * the first parameter represents a starting number;
          * the second parameters show how many consecutive numbers are to be processed
        - two natural numbers and a property to search for;
        - separate the parameters with one space;
        - enter 0 to exit.
        """;
    System.out.println(instructions);
  }
}
