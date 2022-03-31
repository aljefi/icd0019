package fp;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class Numbers {

    private List<Integer> numbers = Arrays.asList(1, 3, 4, 51, 24, 5);

    @Test
    public void findsOddNumbers() {

        List<Integer> oddNumbers = numbers.stream()
                .filter(x -> x % 2 == 1)
                .toList();

        System.out.println(oddNumbers);
    }

    @Test
    public void findsOddNumbersOver10() {

        List<Integer> oddNumbersOver10 = numbers.stream()
                .filter(x -> x % 2 == 1)
                .filter(x -> x > 10)
                .toList();

        System.out.println(oddNumbersOver10);
    }

    @Test
    public void findsSquaredOddNumbers() {

        List<Integer> squareOddNumbers = numbers.stream()
                .filter(x -> x % 2 == 1)
                .map(x -> x * x)
                .toList();

        System.out.println(squareOddNumbers);
    }

    @Test
    public void findsSumOfOddNumbers() {

        Integer sumOddNumbers = numbers.stream()
                .filter(x -> x % 2 == 1)
                .mapToInt(x -> x)
                .sum();

        System.out.println(sumOddNumbers);
    }

}
