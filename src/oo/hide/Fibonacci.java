package oo.hide;

public class Fibonacci {

    int num1 = 0, num2 = 1;
    boolean firstZero = false, firstOne = false;

    public int nextValue() {
        if (!firstZero) {
            firstZero = true;
            return num1;
        } else if (!firstOne) {
            firstOne = true;
            return num2;
        }
        int current = num1 + num2;
        num1 = num2;
        num2 = current;
        return current;
    }

}
