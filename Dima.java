mport java.io.*;
import java.util.*;
import java.util.List;

// 5.03.2023
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
    private int countOfErrors;
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
    public String validator(String word) throws Exception{
        if(word.equals("")){
            throw new Exception("unknown type");
        }else if(word.equals("finish")){
          finishTheTraining();
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
        finishTheTraining();
    }

    public String getInfoOfObject(){
        return word + " - " + translation + " | " + counts + "/5 " + " to end with this word";
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
    public void info(){
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
                    coupleOfWords.countOfErrors++;
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
