package collections.benchmark;

import oo.hide.Timer;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;


public class Runner {

    @Test
    public void benchmarkDifferentImplementations() {

        Set<Integer> set1 = new MySet();
        Set<Integer> set2 = new TreeSet<>();
        Set<Integer> set3 = new HashSet<>();

        Timer timer = new Timer();

        // add 30000 elements to set
        benchmark(set1, timer);
        benchmark(set2, timer);
        benchmark(set3, timer);
    }

    private void benchmark(Set<Integer> set, Timer timer) {
        for (int i = 0; i < 30000; i++) {
            set.add(i);
        }

        System.out.println(timer.getPassedTime());
    }

}
