package collections.demo;

import org.junit.Test;

import java.util.Random;

public class RandomExample {

    @Test
    public void randomExample() {

        Random random = new Random();

        for (int i = 0; i < 10; i++) {
            System.out.print(random.nextInt(10) + " "); // 0-9
        }


    }
    @Test
    public void myRandomExample() {
        MyRandom random = new MyRandom();

        for (int i = 0; i < 10; i++) {
            System.out.print(random.nextInt(10) + " "); // 0-9
        }


    }

    private static class MyRandom {

        private int seed = 1;

        public Integer nextInt(int bound) {
            seed = seed * 19;
            return Math.abs(seed / 10) % bound;
        }

    }

}