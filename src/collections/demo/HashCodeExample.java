package collections.demo;

import oo.hide.Point;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

public class HashCodeExample {

    @Test
    public void hashCodeExample() {

        System.out.println("A".hashCode()); // 65

        System.out.println("A".hashCode()); // 65

        System.out.println("AB".hashCode()); // 2081

        System.out.println("AB".hashCode()); // 2081

        System.out.println(new Object().hashCode());

        System.out.println(new Object().hashCode());
    }

    @Test
    public void hashCodeExample2() {

        Set<Point> set = new HashSet<>();

        set.add(new Point(1, 1));
        set.add(new Point(1, 1));

        System.out.println(set.size());

    }

}

