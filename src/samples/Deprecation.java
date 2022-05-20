package samples;

public class Deprecation {

    public static void main(String[] args) throws Exception {

        new MySampleClass2().someOldMethod();

        // real deprecation example from Java SDK
        MySampleClass2.class.newInstance();
    }

}
