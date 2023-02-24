package AdvancedJava.JUnit;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {
    MathFunctions mathFunctions = new MathFunctions();
    @Test
    void test() {
assertEquals(120, mathFunctions.factorial(5));
    }
    @Test
    void test2(){
      assertEquals(1, mathFunctions.divide(0,3));
    }
}