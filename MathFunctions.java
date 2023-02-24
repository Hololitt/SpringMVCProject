package AdvancedJava.JUnit;
public class MathFunctions {
    public int factorial(int n){
        if(n == 1)
            return 1;
        else
            return n * factorial(n - 1);
    }
    public double divide(double n1, double n2){
        if(n1 == 0)
            return 1;
        else return n1 / n2;
    }
}
