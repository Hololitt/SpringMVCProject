import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("file path");
        Scanner scanner = new Scanner(file);
        while(scanner.hasNextLine()){
            checkWords(scanner.nextLine());
        }
    }
    public static void checkWords(String text){
            Pattern word = Pattern.compile("//word for searching");   //var word = Pattern.compile("");
            Matcher matcher = word.matcher(text);                           //var matcher = word.matcher(text);
            while(matcher.find()){
                System.out.println(matcher.group());
            }
    }
}
