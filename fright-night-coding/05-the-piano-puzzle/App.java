class App {
  public static void main(String[] args) {
    String filePath = "./hyperskill-dataset-117426504.txt";
    String content = ReadFile.readContent(filePath);
    System.out.println("Content: " + content);
    String result = Piano.allNoteSearch(content);
    System.out.println("result: " + result); 
  }
}

