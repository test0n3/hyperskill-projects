import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringRegexCombination {
  public static void main(String[] args) {
    String mainString = "This is a sample string with a combination of characters.";

    // Using String.matches() - checks if the entire string matches the regex
    // This example looks for "sample" followed by any characters, then "string"
    System.out.println(
        "Does the string entirely match the regex '.*sample.*string.*'? " + mainString.matches(".*sample.*string.*"));

    // Using Pattern and Matcher - finds occurrences of the pattern within the
    // string
    Pattern pattern = Pattern.compile("sample.*string"); // Matches "sample", any characters, then "string"
    Matcher matcher = pattern.matcher(mainString);

    System.out.println("Does the string contain the pattern 'sample.*string'? " + matcher.find());
  }
}
