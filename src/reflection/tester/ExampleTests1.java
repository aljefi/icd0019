package reflection.tester;

public class ExampleTests1 {

    @MyTest
    public void test1() { // OK
    }

    @MyTest
    public void test2() { // FAILED
        throw new RuntimeException();
    }

}
