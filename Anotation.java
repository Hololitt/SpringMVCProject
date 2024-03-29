import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface MethodInfo {
    String author() default "Slave";
    int date();
    String purpose();
}
public class Test {
    @MethodInfo(author = "Nikon", date = 2022, purpose = "asdasf")
    public void enter(){
        System.out.println(".....");
    }
}
