public class Template {
    static <T1, T2> void outMerge(T1 t1, T2 t2) {
        System.out.println(t1.toString() + t2);
    }
    public static void main(String[] args) {
        double d = 3.31;
        boolean b = true;
        outMerge(d,b);
    }
}
