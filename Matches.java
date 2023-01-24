package AdvancedJava.Regexp;

public class Matches {
    public static void main(String[] args) {
        String a = "hello";
        System.out.println(a.matches("asf"));     // return true/false

            String string = "-5000";  // if variable string placed number bore than 9 - false                //    \\d - one number
        System.out.println(string.matches("-?\\d*"));    // if add + to \\d  then we get true
                                                             // Or can add * then we can add infinity of numbers
String b = "2138";
String c = "-23412";
String d = "+32552";

        System.out.println(b.matches("((\\+|-)?\\d*)"));
        System.out.println(c.matches("((\\+|-)?\\d*)"));
        d.matches("((\\+|-)?\\d*)");

        String word1 = "jehflishfljSDFDLHBF";
        System.out.println(word1.matches("[a-zA-Z31]+\\d*"));

        String numbers = "10";

        System.out.println(numbers.matches("\\d{2}"));
    }
}
