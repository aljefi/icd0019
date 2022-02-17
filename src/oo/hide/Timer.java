package oo.hide;

public class Timer {

    long a = System.currentTimeMillis();

    public String getPassedTime() {
        long b = System.currentTimeMillis();
        long ret = b - a;
        return Long.toString(ret);
    }
}
