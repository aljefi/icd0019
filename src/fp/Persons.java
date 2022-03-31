package fp;

import org.junit.Test;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


public class Persons {

    private List<Person> persons = List.of(
            new Person(1, "Alice", 22),
            new Person(2, "Bob", 20),
            new Person(3, "Carol", 21));

    @Test
    public void findsPersonById() {
        List<Person> res = persons.stream()
                .filter(p -> p.getId().equals(2))
                .collect(Collectors.toList());

        System.out.println(res);
    }

    @Test
    public void removePersonById() {
        List<Person> res = persons.stream()
                .filter(p -> !p.getId().equals(2))
                .collect(Collectors.toList());

        System.out.println(res);
    }

    @Test
    public void findsPersonNames() {
        String res = persons.stream()
                .map(p -> p.getName())
                .collect(Collectors.joining(", "));

        System.out.println(res);
    }

    @Test
    public void reverseSortedByAge() {
        List<Person> res = persons.stream()
//                .sorted((a, b) -> b.getAge().compareTo(a.getAge()))
                .sorted(Collections.reverseOrder(Comparator.comparing(p -> p.getAge())))
                .collect(Collectors.toList());

        System.out.println(res);
    }

}
