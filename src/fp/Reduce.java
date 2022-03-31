package fp;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class Reduce {

    @Test
    public void reducesListToSingleResult() {

        var numbers = List.of(1, 2, 3, 4);

        assertThat(reduce(numbers, (a, b) -> a + b), is(10));

        assertThat(reduce(numbers, (a, b) -> a * b), is(24));

        assertThat(reduce(List.of("1", "2", "3"),
                (a, b) -> a + b), is("123"));

    }

    private <T> T reduce(List<T> list,
                        BiFunction<T, T, T> func) {

        if (list.isEmpty()) {
            throw new IllegalArgumentException("list cannot be empty");
        }

        ArrayList<T> copy = new ArrayList<>(list);

        T res = copy.remove(0);

        for (T each : copy) {
            res = func.apply(res, each);
        }

        return res;
    }
}
