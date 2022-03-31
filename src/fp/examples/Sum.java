package fp.examples;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class Sum {

    @Test
    public void calculatesSum() {
        List<Integer> numbers = List.of(1, 2, 3, 4);

        double sum = numbers.stream()
                .mapToDouble(each -> each)
                .sum();

        assertThat(sum, is(10));
    }
}


