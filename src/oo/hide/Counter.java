package oo.hide;

public class Counter {

    int current;
    int plus;
    boolean isFirst = true;


    public Counter(int start, int step) {
        current = start;
        plus = step;
    }

    public int nextValue() {
        if (isFirst) {
            isFirst = false;
            return current;
        }
        int ret = current + plus;
        current += plus;
        return ret;
    }
}
