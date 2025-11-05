package com.gerbosan;

import java.util.Map;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.Comparator;

class NumberUtils {
  public static Map<Character, Long> inputCounter(String input) {
    return input.chars()
        .mapToObj(c -> (char) c)
        .collect(Collectors.groupingBy(
            Function.identity(),
            Collectors.counting()));
  }

  public static List<Character> topFourDigits(Map<Character, Long> input) {
    return input.entrySet()
        .stream()
        .sorted(Comparator.<Map.Entry<Character, Long>>comparingLong(Map.Entry::getValue)
            .thenComparing(
                Map.Entry::getKey,
                Comparator.reverseOrder())
            .reversed())
        .limit(4)
        .map(Map.Entry::getKey)
        .collect(Collectors.toList());
  }

  public static char[] bubbleSort(Set<Character> keys, List<Integer> values) {
    char[] orderedCharKeys = new char[values.size()];
    int pos = 0;
    for (Character c : keys) {
      orderedCharKeys[pos++] = c;
    }

    Object[] orderedKeys = keys.toArray();
    int n = values.size();
    boolean swapped;
    for (int i = 0; i < n - 1; i++) {
      swapped = false;
      for (int j = 0; j < n - 1 - i; j++) {
        if (values.get(j) < values.get(j + 1)) {
          int temp = values.get(j);
          Object tempChar = orderedKeys[j];
          char temporalChar = orderedCharKeys[j];
          values.set(j, values.get(j + 1));
          orderedKeys[j] = orderedKeys[j + 1];
          orderedCharKeys[j] = orderedCharKeys[j + 1];
          values.set(j + 1, temp);
          orderedKeys[j + 1] = tempChar;
          orderedCharKeys[j + 1] = temporalChar;
          swapped = true;
        }
      }
      if (!swapped) {
        break;
      }
    }
    System.out.println("values: " + values);
    System.out.println("orderedKeys: " + orderedKeys);
    System.out.print("orderedCharKeys: [");
    for (int i = 0; i < orderedCharKeys.length; i++) {
      System.out.print(orderedCharKeys[i]);
      if (i < orderedCharKeys.length - 1) {
        System.out.print(", ");
      }
    }
    System.out.println("]");
    return orderedCharKeys;
  }
}
