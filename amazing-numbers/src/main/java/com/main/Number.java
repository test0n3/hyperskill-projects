package com.main;

import java.math.BigInteger;

public class Number {
  private String number;

  public Number(String number) {
    this.number = number;
  }

  public String getNumber() {
    return number;
  }

  public void setNumber(String number) {
    this.number = number;
  }

  // check if number is odd or even
  public boolean isNumberParity() {
    return (int) number.charAt(number.length() - 1) % 2 == 0;
  }

  // check if number ends with 7 or is multiple of 7
  public boolean isNumberBuzz() {
    return number.charAt(number.length() - 1) == '7' || isDivisibleBySeven();
  }

  // check if number contains a 0
  public boolean isNumberDuck() {
    return number.contains("0");
  }

  // check if number is palindromic
  public boolean isNumberPalindromic() {
    char[] inputNumber = number.toCharArray();
    int numberLength = inputNumber.length;

    if (numberLength > 1) {

      for (int i = 0; i < numberLength / 2; i++) {
        if (inputNumber[i] != inputNumber[(numberLength - 1) - i]) {
          return false;
        }
      }
    }
    return true;
  }

  // check if number is spy
  public boolean isNumberSpy() {
    char[] inputNumber = number.toCharArray();
    int sum = 0;
    int mul = 1;

    for (int i = 0; i < inputNumber.length; i++) {
      int convertedNumber = Character.getNumericValue(inputNumber[i]);
      sum = sum + convertedNumber;
      mul = mul * convertedNumber;
    }

    if (sum == mul) {
      return true;
    }

    return false;
  }

  // check if number is divisible by seven
  private boolean isDivisibleBySeven() {
    BigInteger current = new BigInteger(this.number);

    while (current.compareTo(BigInteger.valueOf(100)) > 0) {
      BigInteger lastDigit = current.remainder(BigInteger.valueOf(10));
      BigInteger remainingDigits = current.divide(BigInteger.valueOf(10));
      BigInteger doubledLastDigit = lastDigit.multiply(BigInteger.valueOf(2));
      current = remainingDigits.subtract(doubledLastDigit);

      if (current.equals(BigInteger.ZERO)) {
        return true;
      }
    }
    return current.remainder(BigInteger.valueOf(7)).equals(BigInteger.ZERO);
  }

  // check if number is gapful
  public boolean isNumberGapful() {
    if (number.length() < 3) {
      return false;
    } else {
      int wholeNumber = Integer.parseInt(number);
      int divisor = Integer
          .parseInt(String.valueOf(number.charAt(0)) +
              String.valueOf(number.charAt(number.length() - 1)));
      return wholeNumber % divisor == 0;
    }
  }
}
