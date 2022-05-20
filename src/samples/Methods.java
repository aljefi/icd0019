package samples;

import java.lang.reflect.Method;

public class Methods {

    public static void main(String[] args) {

        Method[] methods = MySampleClass.class.getDeclaredMethods();
                                         // vs getMethods();

        for (Method method : methods) {
            System.out.println(method.getName());
            System.out.println(method);
        }

    }
}
