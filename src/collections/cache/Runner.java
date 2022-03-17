package collections.cache;

import oo.hide.Timer;
import org.junit.Test;

public class Runner {

    @Test
    public void calculatesFibonacciOfN() {
        Fibonacci fib = new Fibonacci();

        Timer timer = new Timer();

        for (int i = 0; i < 412; i++) {
            System.out.println(fib.fib(i));
//            fib.fib(i);
        }



        System.out.println(timer.getPassedTime());
    }

}
