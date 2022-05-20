package reflection.tester;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class TestRunner {

    private final List<String> ret = new ArrayList<>();

    public void runTests(List<String> testClassNames) {
        for (String testClassName : testClassNames) {
            try {
                Class<?> aClass = Class.forName(testClassName);
                Object instance = aClass.getDeclaredConstructor().newInstance();
                List<String> tests = new ArrayList<>();
                for (Method method : aClass.getDeclaredMethods()) {
                    if (method.getAnnotation(MyTest.class) != null) {
                        String result = isTestPassed(method, instance) ? "OK" : "FAILED";
                        String s = method.getName() + "()" + " - " + result;
                        tests.add(s);
                    }
                }
                ret.add(tests.toString());
            } catch (ClassNotFoundException | InvocationTargetException | InstantiationException | IllegalAccessException | NoSuchMethodException e) {
                System.out.println(e);
            }
        }
    }

    private boolean isTestPassed(Method method, Object instance) {
        try {
            method.invoke(instance);
        } catch (InvocationTargetException e) {
            MyTest annotation = method.getAnnotation(MyTest.class);
            Class<? extends Throwable> expected = annotation.expected();
            Throwable actual = e.getTargetException();
            return expected.isAssignableFrom(actual.getClass());
        } catch (IllegalAccessException e) {
            System.out.println(e);
        }
        return method.getAnnotation(MyTest.class).expected().isAssignableFrom(MyTest.None.class);
    }

    public String getResult() {
        return ret.toString();
    }
}
