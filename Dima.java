import java.io.*;
import java.util.*;
import java.util.List;

// 4.03.2023
public class Main {
    public static void main(String[] args) throws Exception {
        ArrayList<CoupleOfWords> list = new ArrayList<>();
        CoupleOfWords coupleOfWords = new CoupleOfWords(list);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Set the word by yourself, or copy from file?");
        System.out.println("Enter: set/file");
        String kindOfStart = coupleOfWords.validator(scanner.nextLine());
        if(kindOfStart.equals("set")){
            coupleOfWords.setWords();
        }else if(kindOfStart.equals("file")){
            coupleOfWords.setElementsFromFileToList();
        }else{
            throw new Exception();
        }
        coupleOfWords.printInFile();
        coupleOfWords.startTraining();
    }
}
class CoupleOfWords {
    private int counts = 0;
    private String word;
    private String translation;
    private ArrayList<CoupleOfWords> list = new ArrayList<>();
   private final Scanner scanner = new Scanner(System.in);
    private final String filePath = "C:\\Users\\holol\\Desktop\\IdeaProjects\\first\\src\\Training\\TrainingOfWords\\Words.txt";
    public CoupleOfWords(ArrayList<CoupleOfWords> list) {
        this.list = list;
    }
    public CoupleOfWords(String word, String translation) {
        this.word = word;
        this.translation = translation;
    }
    public void help(){
        System.out.println("<finish> to finish the program");
        System.out.println("<skip> to skip current word");
    }
    public void setWords(){
        var scanner = new Scanner(System.in);
        System.out.println("Enter your words for learning");
        try {
            while(true) {
                System.out.print("Enter the word: ");
                String word = validator(scanner.nextLine());
                if(word.equals("ready"))
                    break;
                System.out.print("Enter the translation for to this word: ");
                String translation = validator(scanner.nextLine());
                if(translation.equals("ready"))
                    break;
                if(translation.equals("help")){
                    help();
                }else{
                    list.add(new CoupleOfWords(word, translation));
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public String validator(String word) throws Exception{
        if(word.equals("")){
            throw new Exception("unknown type");
        }else if(word.equals("finish")){
            System.out.println("Program is finished");
            System.exit(0);
        }
        return word;
    }
    public void printInFile() throws IOException {
        var fileWriter = new FileWriter(filePath);
        StringBuilder sb = new StringBuilder();
        for (var indexOfObject : list) {
            sb.append(indexOfObject.word).append(System.lineSeparator());
            sb.append(indexOfObject.translation).append(System.lineSeparator());
        }
        fileWriter.write(sb.toString());
        fileWriter.close();
    }
    public void setElementsFromFileToList() throws Exception {
        var file = new File(filePath);
        var scanner = new Scanner(file);
        while(scanner.hasNextLine()){
            list.add(new CoupleOfWords(validator(scanner.nextLine()), validator(scanner.nextLine())));
        }
    }
    public void startTraining() {
        var random = new Random();
        while(!list.isEmpty()){
            var coupleOfWords = list.get(random.nextInt(0, list.size()));
           check(coupleOfWords);
        }
        System.out.println("Program is finished");
    }
    public String toString(){
        return word + " " + translation;
    }
    public void check(CoupleOfWords coupleOfWords) {
        var random = new Random();
        List<String> wordAndTranslation = new ArrayList<>();
        wordAndTranslation.add(coupleOfWords.word);
        wordAndTranslation.add(coupleOfWords.translation);
        int numberOfVariable = random.nextInt(0, wordAndTranslation.size());
        System.out.println("write translation to word "+ wordAndTranslation.get(numberOfVariable));
        wordAndTranslation.remove(numberOfVariable);
        String translationFromUser = scanner.nextLine();
       checkTranslationFromUser(translationFromUser, coupleOfWords, wordAndTranslation);
    }
    private void checkTranslationFromUser(String translationFromUser, CoupleOfWords coupleOfWords, List<String> wordAndTranslation){
        switch(translationFromUser){
            case "finish" -> {
                System.out.println("Program is finished");
                System.exit(0);
            }case "skip" -> {
                System.out.println("This word was skipped");
                list.remove(coupleOfWords);
            }case "help" -> {
                help();
                checkTranslationFromUser(scanner.nextLine(), coupleOfWords, wordAndTranslation);
            }
            default -> {
                String reset = "\u001B[0m";
                if(translationFromUser.equals(wordAndTranslation.get(0))){
                    String green = "\u001B[32m";
                    coupleOfWords.counts++;
                    System.out.println(green +"True     "+coupleOfWords.counts+"/5"+ reset);
                }else {
                    coupleOfWords.counts--;
                    String red = "\u001B[31m";
                    System.out.println(red+"False!"+ reset);
                    System.out.println("Write answer was "+wordAndTranslation.get(0));
                }
                if(coupleOfWords.counts == 5){
                    list.remove(coupleOfWords);
                    System.out.println("You learned this word");
                }
            }
        }
    }
}
