package samples;

import java.lang.reflect.Field;

public class Fields {

    public static void main(String[] args) {

        Field[] fields = MySampleClass.class.getDeclaredFields();

        for (Field field : fields) {
            System.out.println(field.getName());
            System.out.println(field.getType());
        }

    }
}
