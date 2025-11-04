package com.gerbosan;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.Comparator;
import java.util.Arrays;

public class App {
  public static void main(String[] args) {
    System.out.println("Hello World!");
    String filePath = "hyperskill-dataset-117267365.txt";
    // String data = ReadFile.returnContent(filePath);
    // String data = "2904612814265124950186150241288486039555324630611109544186388550429967794529563707050650703320339036303462335384680001635099845625207021105726257804565606103838537290638121317541338115759138705042919783275742443076834276284559804555328509747993798405812273351973853800426683080375361218215688342894425548144853863719765915342592038704966737115331740496229192849607808619449322065100883244874724693566475782324898011670251914418418718579383005330365012983996394588625731220697740582918132951070624189748427652655102941025460326238168694857579440451283111900752703048105388636238067658728210614836152288944156940396556095730684424451732507757790626475790839367618298518731312847741549804680633927465036036579854814916390958269034498994208685838734728553904457922664987577356093436259903298968562438875139158936937832960385003877163146471220908310078215168637856163130372503106190164118392536797483029739735657830572903380017720291452050906375262810448584797102934741205694274618037452868617519789852780";
    Map<Integer, Long> result = NumberUtils.inputCounter(data);
    System.out.println("HashMap of frequency: " + result);
    // get HashMap of frequency {0=106, 1=92, 2=95, 3=110, 4=97, 5=107, 6=95, 7=94,
    // 8=112, 9=92}

    List<Integer> topFour = NumberUtils.topFourDigits(result);
    System.out.println(topFour); // get list of the four most frequent numbers: [8, 3, 5, 0, 4, 2, 6, 7, 1, 9]

  }
}

class NumberUtils {
  public static Map<Integer, Long> inputCounter(String input) {
    return Arrays.stream(input.split(""))
        .map(Integer::parseInt)
        .collect(Collectors.groupingBy(
            Function.identity(),
            Collectors.counting()));
  }

  public static List<Integer> topFourDigits(Map<Integer, Long> input) {
    return input.entrySet()
        .stream()
        .sorted(Comparator.<Map.Entry<Integer, Long>>comparingLong(Map.Entry::getValue)
            // .reversed()
            .thenComparingInt(Map.Entry::getKey)
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

class ReadFile {
  public static String returnContent(String filePath) {
    String content = "";

    try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
      while ((content = reader.readLine()) != null) {
        System.out.println(content);
        return content;
      }
    } catch (IOException e) {
      System.err.println("Error reading file: " + e.getMessage());
    }
    return content;
  }
}
