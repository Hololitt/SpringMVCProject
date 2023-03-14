
import java.io.*;
import java.util.*;
import java.util.List;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.time.Instant;
import java.time.Duration;
// 14.03.2023
public class Main {
    public static void main(String[] args) throws Exception {
        ArrayList<CoupleOfWords> list = new ArrayList<>();
        CoupleOfWords coupleOfWords = new CoupleOfWords(list);
BaseOfWords baseOfWords = new BaseOfWords(list);

        coupleOfWords.setKindOfStart();
        coupleOfWords.printInFile();
        baseOfWords.printLearnedWordInBase();
        coupleOfWords.startTraining();
    }
}
class CoupleOfWords {
    private int counts = 0;
    private String word;
    private String translation;
    private int countOfErrors;
    private String totalTime;
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
    public String toString(){
        return word + " - " + translation;
    }
    public String getWord(){
        return word;
    }
    public void setKindOfStart() throws Exception {
        System.out.println("Set the word by yourself, or copy from file?");
        System.out.println("Enter: set/file or <repeat> to repeat all learned word");
        String kindOfStart = scanner.nextLine();
        switch(kindOfStart){
            case "set" -> setWords();
            case "file" -> setElementsFromFileToList(filePath);
            case "help" -> {
                help();
                setKindOfStart();
            }case "repeat" -> {
                var baseOfWords = new BaseOfWords(list);
                setElementsFromFileToList(baseOfWords.getFilePath());
            }
            default -> {
                System.out.println("Unknown value");
                setKindOfStart();
            }
        }
    }
    private void help(){
        System.out.println(yellow + "<finish> to finish the program");
        System.out.println("<skip> to skip current word");
        System.out.println("<info> show info" + reset);
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
                else
                    list.add(new CoupleOfWords(word, translation));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    private String validator(String word) {
        try{
            if(word.equals("")){
                throw new Exception("unknown type");
            }else if(word.equals("finish")){
                finishTheTraining();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return word;
    }
    public void printInFile() throws IOException {
        var fileWriter = new FileWriter(filePath);
        StringBuilder sb = new StringBuilder();
        for (var indexOfObject : list) {
            sb.append(indexOfObject.word).append(" - ").append(indexOfObject.translation).append(System.lineSeparator());
        }
        fileWriter.write(sb.toString());
        fileWriter.close();
    }

    public void setElementsFromFileToList(String filePath) throws Exception {
        var file = new File(filePath);
        var scanner = new Scanner(file);
        while(scanner.hasNextLine()){
            var sentence = scanner.nextLine();
            String word, translation;
            word = sentence.substring(sentence.lastIndexOf("-")+2);

            sentence = sentence.replace(word, "");
            translation = sentence.replace(" - ", "");

            list.add(new CoupleOfWords(validator(word), validator(translation)));
        }
    }
    public void startTraining() {
        var random = new Random();
        Instant start = Instant.now();
        while(!list.isEmpty()){
            var coupleOfWords = list.get(random.nextInt(0, list.size()));
            check(coupleOfWords);
        }
        Instant finish = Instant.now();
       totalTime = totalTimeAndHisType(Duration.between(start, finish));
        finishTheTraining();
    }
    private String totalTimeAndHisType(Duration totalTime){
       if(totalTime.toSeconds() > 120){
           return totalTime.toMinutes() + " minutes";
       }
       else return totalTime.toSeconds() + " seconds";
    }
    public String getInfoOfObject(){
        return word + " - " + translation + " | " + counts + "/5 " + " to end with this word";
    }
    private void check(CoupleOfWords coupleOfWords) {
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
    private void info(){
        StringBuilder sb = new StringBuilder();
        for(var info : list){
            sb.append(info.getInfoOfObject()).append(System.lineSeparator());
        }
        System.out.println(yellow + sb + reset);
    }
    private final String yellow = "\u001B[33m";
    private final String reset = "\u001B[0m";
    private void finishTheTraining(){
        System.out.println("Training is finished");
        System.out.println("You did "+countOfErrors+" errors!");
        System.out.println("You learned these word in " + totalTime);
        System.exit(0);
    }
    private void checkTranslationFromUser(String translationFromUser, CoupleOfWords coupleOfWords, List<String> wordAndTranslation){
        switch(translationFromUser){
            case "finish" -> finishTheTraining();
            case "skip" -> {
                System.out.println("This word was skipped");
                list.remove(coupleOfWords);
            }case "help" -> {
                help();
                checkTranslationFromUser(scanner.nextLine(), coupleOfWords, wordAndTranslation);
            }
            case "info" -> {
                info();
                checkTranslationFromUser(scanner.nextLine(), coupleOfWords, wordAndTranslation);
            }
            default -> {
                if(translationFromUser.equals(wordAndTranslation.get(0))){
                    String green = "\u001B[32m";
                    coupleOfWords.counts++;
                    System.out.println(green +"True     "+coupleOfWords.counts+"/5"+ reset);
                }else {
                    if(coupleOfWords.counts > -5){
                        coupleOfWords.counts--;
                    }
                    countOfErrors++;
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
class BaseOfWords {
    private final ArrayList<CoupleOfWords> list;
    private final File file = new File(
            "C:\\Users\\holol\\Desktop\\IdeaProjects\\first\\src\\AdvancedJava\\Patterns\\ListOfLearnedWords.txt");
    public String getFilePath(){
        return file.getPath();
    }
    BaseOfWords(ArrayList<CoupleOfWords> list){
        this.list = list;
    }
    public void printLearnedWordInBase() throws IOException {
        for(var l : list){
            if(!checkSameWordInBase(l.getWord())){
                PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(file, true)));
                out.println(l);
                out.close();
            }
        }
    }
    private boolean checkSameWordInBase(String wordForCheck) throws IOException{
        var scanner = new Scanner(file);
        var text = new StringBuilder();
        while(scanner.hasNextLine()){
            text.append(scanner.nextLine()).append(System.lineSeparator());
        }
        var pattern = Pattern.compile(wordForCheck);
        Matcher matcher = pattern.matcher(text);
        return matcher.find();
    }
}
