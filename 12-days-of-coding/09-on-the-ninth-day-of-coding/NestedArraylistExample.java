import java.util.ArrayList;
import java.util.Arrays;

public class NestedArrayListExample {
  public static void main(String[] args) {
    // Declaration and initialization of the nested ArrayList
    ArrayList<ArrayList<int[]>> dataStructure = new ArrayList<>();

    // 1. Create inner ArrayLists (rows) and int arrays (elements)

    // First row: an ArrayList holding int arrays
    ArrayList<int[]> row1 = new ArrayList<>();
    int[] arr1_1 = { 1, 2 };
    int[] arr1_2 = { 3, 4, 5 };
    row1.add(arr1_1);
    row1.add(arr1_2);

    // Second row: another ArrayList holding different int arrays
    ArrayList<int[]> row2 = new ArrayList<>();
    int[] arr2_1 = { 6, 7, 8 };
    int[] arr2_2 = { 9 };
    row2.add(arr2_1);
    row2.add(arr2_2);

    // 2. Add the inner ArrayLists to the main ArrayList
    dataStructure.add(row1);
    dataStructure.add(row2);

    // 3. Accessing elements
    System.out.println("--- Accessing elements ---");
    // Access the second element (index 1) of the first inner ArrayList (index 0)
    // This gives you the int[] {3, 4, 5}
    int[] retrievedArray = dataStructure.get(0).get(1);
    System.out.println("Retrieved array from [0][1]: " + Arrays.toString(retrievedArray));

    // Access a specific integer within that retrieved array (e.g., the value 4 at
    // index 1)
    int value = dataStructure.get(0).get(1)[1];
    System.out.println("Value at [0][1][1]: " + value);

    // 4. Iterating through the entire structure
    System.out.println("\n--- Iterating through the structure ---");
    for (ArrayList<int[]> innerList : dataStructure) {
      for (int[] intArray : innerList) {
        System.out.println(Arrays.toString(intArray));
      }
    }
  }
}
