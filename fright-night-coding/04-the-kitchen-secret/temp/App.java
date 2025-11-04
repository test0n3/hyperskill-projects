public class App {
  public static void main(String[] args) {
    KeyPad.printKeyPad();
    // Scanner input = new Scanner(System.in);
    // String filePath = input.nextLine();
    String filePath = "./hyperskill-dataset-117414675.txt";
    String content = ReadFile.readFile(filePath);
    String[][] instructions = StringUtils.inputToArray(content);
    KeyPad.printKeyPadString(instructions);

    // input.close();
  }
}
// RIGHT,RIGHT,LEFT,DOWN,UP,RIGHT,UP,RIGHT,LEFT,UP,UP,LEFT,DOWN,UP,LEFT,DOWN,RIGHT,LEFT,RIGHT,UP,DOWN,LEFT,LEFT,DOWN,DOWN,DOWN,DOWN,DOWN,RIGHT
// RIGHT,RIGHT,LEFT,RIGHT,LEFT,RIGHT,LEFT,UP,LEFT,UP,RIGHT,UP,UP,DOWN,UP
