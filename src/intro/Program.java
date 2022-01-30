package intro;

import java.util.Objects;

public class Program {

    public static void main(String[] args) {
        int decimal = asDecimal("11001101");
        System.out.println(decimal); // 205
        System.out.println(asString(11001101));
        System.out.println(pow(3, 2));
    }

    public static String asString(int input) {
        return String.valueOf(input);
    }

    public static int asDecimal(String input) {
        int ret = 0;
        int temp = 1;
        input = new StringBuilder(input).reverse().toString();
        for (String c : input.split("")) {
            if (Objects.equals(c, "1")) {
                ret += temp;
            }
            temp = temp * 2;
        }
        return ret;
    }

    private static int pow(int arg, int power) {
        int ret = 1;
        while (power != 0) {
            ret = ret * arg;
            power--;
        }
        return ret;
    }
}
