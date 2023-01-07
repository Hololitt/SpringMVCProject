package AdvancedJava.LambdaExpression;
interface Executable{
    int execute(int x, int y);
}
class Runner{
    public void run(Executable executable){
int number = executable.execute(10, 23);
        System.out.println(number);
    }
}

public class LambdaExpression1 {
    public static void main(String[] args) {
        Runner runner = new Runner();

        runner.run(Integer::sum);

        runner.run(Integer::sum);
    }
}       
