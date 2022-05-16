package reflection.tester;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

public class TestRunner {

    String result = "\n";

    public void runTests(List<String> testClassNames) {
        for (String testClassName : testClassNames) {
            try {
                Class<?> testClass = getTestClass(testClassName);
                Method[] methods = getMethods(testClass);

                try { // ExampleTests1
                    ExampleTests1 instance1 = new ExampleTests1();
                    try {
                        methods[0].invoke(instance1);
                        result += methods[0].getName() + "() - OK \n"; // test1
                    } catch (Exception s) {
                        extracted(testClassName, s);
                    }
                } catch (Exception e) { // ExampleTests2
                    ExampleTests2 instance2 = new ExampleTests2();
                    try {
                        methods[1].invoke(instance2);
                        result += methods[1].getName() + "() - OK \n"; // test1
                    } catch (Exception s) {
                        extracted(testClassName, s);
                    }
                }
            } catch (Exception e) {
                extracted(testClassName, e);
            }
        }
    }

    private void extracted(String testClassName, Exception e) {
        Class<?> testClass = getTestClass(testClassName);
        Method[] methods = getMethods(testClass);
        for (Method method : methods) {
            if (method.getAnnotation(MyTest.class) == null) {
                continue;
            }
            Class<? extends Throwable> annotation = getAnnotation(method);
            Throwable error = e.getCause();
            System.out.println(Arrays.toString(e.getStackTrace()));

            System.out.println(method.getName());
            System.out.println(annotation.getName());
            System.out.println(error.toString());

            if (error.toString().equals("java.lang.IllegalStateException")
                    && annotation.getName().equals("java.lang.RuntimeException")) {
                 System.out.println(1);
                result += method.getName() + "() - OK \n";
            }
            else if (error.toString().equals("java.lang.RuntimeException")
                    && annotation.getName().equals("java.lang.IllegalStateException")) {
                System.out.println(2);
                result += method.getName() + "() - FAILED \n";
            } else if (error.toString().equals("java.lang.IllegalStateException")
                    && annotation.getName().equals("java.lang.IllegalStateException")) {
                System.out.println(3);
                result += method.getName() + "() - OK \n";
            }
            else {
                 System.out.println(4);
                result += method.getName() + "() - FAILED \n";
            }
            System.out.println();
        }
    }

    private Class<?> getTestClass(String testClassName) {
        try {
//            System.out.println("Class:          " + Class.forName(testClassName));
            return Class.forName(testClassName);
        } catch (ClassNotFoundException e) {
            System.out.println("testClassName");

        }
        return null;
    }

    private Method[] getMethods(Class<?> testClass) {
        Method[] methods;
        methods = testClass.getDeclaredMethods();
//        System.out.println("Methods:         " + Arrays.toString(methods));
        return methods;
    }

    private Class<? extends Throwable> getAnnotation(Method method) {
        try {
            Class<? extends Throwable> annotation = method.getAnnotation(MyTest.class).expected();
//            System.out.println("Annotation:     " + annotation.getName());
            return annotation;
        } catch (NullPointerException e) {
//            System.out.println("getAnnotation");
            return null;
        }

    }

    public String getResult() {
        return result;
    }
}
