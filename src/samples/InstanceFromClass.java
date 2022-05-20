package samples;

public class InstanceFromClass {

    public static void main(String[] args) throws Exception {

        Class<MySampleClass> clazz = MySampleClass.class;

        MySampleClass instance = clazz.getDeclaredConstructor().newInstance();

        instance.hello();
    }

}
