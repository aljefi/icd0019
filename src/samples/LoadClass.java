package samples;

import java.lang.reflect.Field;

public class LoadClass {

    public static void main(String[] args) throws Exception {

        Class<?> clazz = Class.forName("samples.MySampleClass");

        // .class file should be on classpath
        // java -cp ./my-class-path ...

        for (Field field : clazz.getDeclaredFields()) {
            System.out.println(field.getName());
        }
    }
}
