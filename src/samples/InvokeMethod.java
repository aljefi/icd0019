package samples;

import java.lang.reflect.Method;

public class InvokeMethod {

    public static void main(String[] args) throws Exception {

        Method method = MySampleClass.class.getDeclaredMethod("hello");

        MySampleClass instance = new MySampleClass();

        method.invoke(instance);
    }

}
