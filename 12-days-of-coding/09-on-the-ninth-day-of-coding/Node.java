import java.util.Objects;

class Node {
  int r, c;

  Node(int r, int c) {
    this.r = r;
    this.c = c;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    Node node = (Node) o;
    return r == node.r && c == node.c;
  }

  @Override
  public int hashCode() {
    return Objects.hash(r, c);
  }
}
