class App {
  public static void main(String[] args) {
    String filePath = "./hyperskill-dataset-119047345.txt";
    String content = ReadFile.readContent(filePath);
    String[] log = content.split("\n");
    int contentions = 0;
    boolean[] userList = { true, true, true, true, true };

    for (String line : log) {
      String[] userAction = line.split(",");
      int forkId = Integer.parseInt(userAction[2]) - 1;
      // String user = userAction[0];
      String action = userAction[1];
      switch (action) {
        case "pick":
          if (!userList[forkId]) {
            contentions++;
          } else {
            userList[forkId] = false;
          }
          break;
        case "release":
          userList[forkId] = true;
          break;
        default:
      }
    }
    System.out.println("contentions: " + contentions);
  }
}
