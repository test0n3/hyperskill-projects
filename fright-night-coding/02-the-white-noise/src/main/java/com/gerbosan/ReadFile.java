package com.gerbosan;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

class ReadFile {
  public static String returnContent(String filePath) {
    String content = "";

    try {
      content = Files.lines(Paths.get(filePath))
          .collect(Collectors.joining());

    } catch (IOException e) {
      System.err.println("File reading error: " + e.getMessage());
    }
    return content;
  }
}
