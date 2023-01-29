public class Training35 {
    public static void main(String[] args) {
        System.out.println(function("function"));
        System.out.println(function(123123, 434199));
        System.out.println(function(16));
    }
    public static long function(long n){
        if(n<=0)
            return 1;
        else
            return n * function(n -1);
    }
    public static int function(String word){
        return word.length();
    }
    public static long function(long n1, long n2){
        return n1 * n2;
    }
}
