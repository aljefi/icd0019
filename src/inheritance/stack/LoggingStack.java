package inheritance.stack;

import java.util.Stack;

public class LoggingStack extends Stack<Integer> {

    @Override
    public Integer push(Integer item) {
        System.out.println("pushed item: " + item);

        return super.push(item);
    }

    @Override
    public Integer pop() {
        Integer popped = super.pop();
        System.out.println("popped elem: " + popped);
        return popped;
    }

    public void pushAll(Integer... numbers) {
        for (Integer i: numbers) {
            push(i);
        }
    }
}
