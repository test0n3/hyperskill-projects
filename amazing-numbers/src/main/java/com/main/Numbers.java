package com.main;

public class Numbers {
  private String firstParameter;
  private String secondParameter;
  private String thirdParameter;

  public Numbers(String[] inputs) {
    this.firstParameter = inputs[0];
    this.secondParameter = inputs.length > 1 ? inputs[1] : null;
    this.thirdParameter = inputs.length > 2 ? inputs[2] : null;
  }

  public void execute() {
    if (secondParameter == null && thirdParameter == null) {
      Number number = new Number(firstParameter);
      NumberUtils.printNumberProperties(number);
      return;
    }

    if (secondParameter != null && thirdParameter == null) {
      int limit = Integer.parseInt(secondParameter);
      for (int i = 0; i < limit; i++) {
        Number number = new Number(String.valueOf(Integer.parseInt(firstParameter) + i));
        NumberUtils.printSimpleNumberProperties(number);
      }
      return;
    }

    if (secondParameter != null && thirdParameter != null) {
      int iteration = 0;
      int limit = Integer.parseInt(secondParameter);
      String number;

      do {
        number = String.valueOf(Integer.parseInt(firstParameter) + iteration);
        if (findNumberByProperty(number)) {
          NumberUtils.printSimpleNumberProperties(new Number(number));
          limit--;
        }
        iteration++;
      } while (limit > 0);
    }
  }

  private boolean findNumberByProperty(String value) {
    Number number = new Number(value);

    switch (thirdParameter.toUpperCase()) {
      case "BUZZ":
        return number.isNumberBuzz();
      case "DUCK":
        return number.isNumberDuck();
      case "PALINDROMIC":
        return number.isNumberPalindromic();
      case "GAPFUL":
        return number.isNumberGapful();
      case "SPY":
        return number.isNumberSpy();
      case "EVEN":
        return number.isNumberParity();
      case "ODD":
        return !number.isNumberParity();
      default:
        System.out.println("no property required");
    }
    return false;
  }
}
