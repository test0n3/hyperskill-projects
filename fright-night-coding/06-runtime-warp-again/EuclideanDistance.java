class EuclideanDistance {
  public static long euclideanDistance(int[][] input) {
    long distance = 0l;
    for (int i = 0; i < input.length - 1; i++) {
      int[] p1 = input[i];
      int[] p2 = input[i + 1];

      double powSum = Math.pow(p2[0] - p1[0], 2) +
          Math.pow(p2[1] - p1[1], 2) +
          Math.pow(p2[2] - p1[2], 2) +
          Math.pow(p2[3] - p1[3], 2);
      distance += (long) Math.ceil(Math.sqrt(powSum));
    }
    return distance;
  }
}
