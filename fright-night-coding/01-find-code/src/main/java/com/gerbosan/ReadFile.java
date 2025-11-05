package com.gerbosan;

import java.io.IOException;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.util.stream.Collectors;
import java.io.BufferedReader;
import java.io.FileReader;

class ReadFile {
  public static String returnContent(String filePath) {
    String content = "";

    // try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
    // while ((content = reader.readLine()) != null) {
    // System.out.println(content);
    // return content;
    // }
    // } catch (IOException e) {
    // System.err.println("Error reading file: " + e.getMessage());
    // }
    try {
      content = Files.lines(Paths.get(filePath)).collect(Collectors.joining());
    } catch (IOException e) {
      System.err.println("File reading error: " + e.getMessage());
    }
    return content;
  }
}
