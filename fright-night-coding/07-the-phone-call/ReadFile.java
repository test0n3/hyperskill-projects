import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

class ReadFile {
  public static String readContent(String filePath) {
    try {
      String content = Files.readString(Paths.get(filePath));
      System.out.println("File read successfully. Total length: " + content.length());
      return content;
    } catch (IOException e) {
      System.err.println("Error reading file: " + e.getMessage());
      return null;
    }
  }
}
