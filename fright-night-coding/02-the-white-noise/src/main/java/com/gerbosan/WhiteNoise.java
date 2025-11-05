package com.gerbosan;

import java.util.Arrays;

class WhiteNoise {
  public static int returnDegrees(String input) {
    String[] inputData2 = input.split(",");
    Integer sum = Arrays.stream(inputData2)
        .map(Integer::parseInt)
        .reduce(0, (a, b) -> a + b);
    System.out.println("sum: " + sum);

    int residue = sum % 360;
    if (residue < 0) {
      return (360 - residue);
    } else {
      return residue;
    }
  }
}
