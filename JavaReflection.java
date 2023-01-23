import java.lang.reflect.*;

public class JavaReflection {
    public static void main(String[] args) throws IllegalAccessException, InvocationTargetException {
Block block = new Block(10,12);
Field[] field = block.getClass().getDeclaredFields();
for(Field blockFields : field){
    blockFields.setAccessible(true);
    blockFields.set(block, 1);
    System.out.println(block.getNumber());
}
Method[] method = block.getClass().getDeclaredMethods();
for(Method methods : method){
    System.out.println(methods.getName());
    if(methods.getName().equals("start")){
        System.out.println(methods.invoke(null));
    }
}
}}
class Block{
    @Override
    public String toString() {
        return "Block{" +
                "number = " + number +
                ", number2 = " + number2 +
                '}';
    }

    public Block(int number, int number2){
        this.number = number;
        this.number2 = number2;
    }
    private final int number;
    private final int number2;
    public int getNumber(){
        return number;
    }
    public int getNumber2(){
        return number2;
    }
    public static void start(){
        for(int i = 10; i > 0; i--){
            System.out.print(i+" ");
        }
    }
}
