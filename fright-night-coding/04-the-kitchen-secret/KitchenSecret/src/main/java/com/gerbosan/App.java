package com.gerbosan;

// import java.util.Scanner;

public class App {
  public static void main(String[] args) {
    KeyPad.printKeyPad();
    // Scanner input = new Scanner(System.in);
    // String filePath = input.nextLine();
    String filePath = "./hyperskill-dataset-117380127.txt";
    String content = ReadFile.readFile(filePath);
    String[][] instructions = StringUtils.inputToArray(content);
    KeyPad.printKeyPadString(instructions);

    // input.close();
  }
}
