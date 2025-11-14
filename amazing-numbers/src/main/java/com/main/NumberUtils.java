package com.main;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Arrays;

public class NumberUtils {
  private static final String[] PROPERTIES = new String[] { "BUZZ", "DUCK", "PALINDROMIC", "GAPFUL",
      "SPY", "EVEN", "ODD" };

  public static boolean isNatural(String input) {
    return input.matches("^[0-9]+$");
  }

  public static int checkInputs(String[] inputs) {
    boolean result = true;
    int errorId = 0;
    List<String> subArray = Arrays.stream(inputs)
        .limit(2)
        .collect(Collectors.toList());

    for (String input : subArray) {
      result = result && isNatural(input);
    }

    if (!result) {
      System.out.println("The first parameter should be a natural number or zero.");
      errorId += 1;
    }

    if (inputs.length == 3 && !isValidPropertyRequest(inputs[2])) {
      System.out.println("The property [" + inputs[2].toUpperCase() + "] is wrong.");
      System.out.println("Available properties: " + Arrays.toString(PROPERTIES) + ".");
      errorId += 1;
    }

    return errorId;
  }

  public static boolean anyZeros(String[] inputs) {
    boolean result = false;
    List<String> subArray = Arrays.stream(inputs)
        .limit(2)
        .collect(Collectors.toList());

    for (String input : subArray) {
      result = result || input.equals("0");
    }

    return result;
  }

  // print number properties
  public static void printNumberProperties(Number number) {
    System.out.println("\nProperties of " + formatNumber(number.getNumber()));
    System.out.println("        buzz: " + number.isNumberBuzz());
    System.out.println("        duck: " + number.isNumberDuck());
    System.out.println(" palindromic: " + number.isNumberPalindromic());
    System.out.println("      gapful: " + number.isNumberGapful());
    System.out.println("         spy: " + number.isNumberSpy());
    System.out.println("        even: " + number.isNumberParity());
    System.out.println("         odd: " + !number.isNumberParity());
  }

  public static void printSimpleNumberProperties(Number number) {
    List<String> properties = new ArrayList<>();
    if (number.isNumberBuzz()) {
      properties.add("buzz");
    }

    if (number.isNumberDuck()) {
      properties.add("duck");
    }

    if (number.isNumberPalindromic()) {
      properties.add("palindromic");
    }

    if (number.isNumberGapful()) {
      properties.add("gapful");
    }

    if (number.isNumberSpy()) {
      properties.add("spy");
    }

    if (number.isNumberParity()) {
      properties.add("even");
    } else {
      properties.add("odd");
    }

    System.out.println("              " + formatNumber(number.getNumber()) + " is " + String.join(", ", properties));
  }

  // format number for printing properties
  private static String formatNumber(String number) {
    if (number.length() <= 3) {
      return number;
    }

    StringBuilder formatedNumber = new StringBuilder();
    char[] inputNumber = number.toCharArray();
    int commaPos = 0;

    for (int i = inputNumber.length - 1; i >= 0; i--) {
      if (commaPos == 3) {
        formatedNumber.insert(0, ",");
        commaPos = 0;
      }
      formatedNumber.insert(0, inputNumber[i]);
      commaPos++;
    }
    return formatedNumber.toString();
  }

  // check if requestedProperty is valid compared to PROPERTIES
  private static boolean isValidPropertyRequest(String requestedProperty) {
    return Arrays.asList(PROPERTIES).contains(requestedProperty.toUpperCase());
  }
}
