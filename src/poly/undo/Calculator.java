package poly.undo;

import generics.stack.Stack;

import java.util.function.Function;

public class Calculator {

    private double value;

    private Stack<Function<Double, Double>> undos = new Stack<>();

    public void input(double value) {
        double copy = this.value;
        undos.push(input -> copy);
        this.value = value;
    }

    public void add(double addend) {
        undos.push(input -> this.value - addend);
        value += addend;
    }

    public void multiply(double multiplier) {
        undos.push(input -> this.value / multiplier);
        value *= multiplier;
    }

    public double getResult() {
        return value;
    }

    public void undo() {
        Function<Double, Double> function = undos.pop();
        value = function.apply(value);
    }
}
