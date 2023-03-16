
import java.io.*;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner s = new Scanner(System.in);
        System.out.println("Enter your file path");
        String filePath = s.nextLine();
        File file = new File(filePath);
        Scanner scanner = new Scanner(file);
        System.out.println("Enter the text which you would to search in file");
        String textForSearching = scanner.nextLine();
        while(scanner.hasNextLine()){
            checkWords(scanner.nextLine(),textForSearching);
        }
    }
    public static void checkWords(String text, String textForSearching){
            Pattern word = Pattern.compile(textForSearching);
            Matcher matcher = word.matcher(text);
            while(matcher.find()){
                System.out.println(matcher.group().length() + "coincidences");
            }
    }
}
