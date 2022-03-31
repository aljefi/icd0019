package fp.sales;

import java.time.LocalDate;
import java.util.*;

public class Analyser {

    private final Repository repository;

    Double sum = 0.0;
    int count = 0;

    public Analyser(Repository repository) {
        this.repository = repository;
    }

    public Double getTotalSales() {
        List<Entry> sales = repository.getEntries();
        sales.subList(1, sales.size()).stream()
                .map(entry -> entry.getAmount())
                .forEach(amount -> sum += amount);
        return sum;
    }

    public Double getSalesByCategory(String category) {
        List<Entry> sales = repository.getEntries();
        sales.subList(1, sales.size()).stream()
                .filter(entry -> entry.getCategory().equals(category))
                .map(entry -> entry.getAmount())
                .forEach(amount -> {
                    if (amount != null) {
                        sum += amount;
                    }
                });
        return sum;
    }

    public Double getSalesBetween(LocalDate start, LocalDate end) {
        List<Entry> sales = repository.getEntries();
        sales.subList(1, sales.size()).stream()
                .filter(entry -> entry.getDate().isAfter(start))
                .filter(entry -> entry.getDate().isBefore(end))
                .map(entry -> entry.getAmount())
                .forEach(amount -> sum += amount);
        return sum;
    }

    public String mostExpensiveItems() {
        List<Entry> sales = repository.getEntries();
        List<String> temp = new ArrayList<>();
        StringBuilder ret = new StringBuilder();

        sales.subList(1, sales.size()).stream()
                .sorted(Collections.reverseOrder(Comparator.comparing(p -> p.getAmount())))
                .map(entry -> entry.getProductId())
                .forEach(item -> {
                    if (count != 3) {
                        count++;
                        temp.add(item);
                    }
                });

        temp.sort(Comparator.reverseOrder());
        Collections.reverse(temp);
        temp.forEach(elem -> {
            if (count != 6) {
                count++;
                ret.append(elem);
                if (count != 6) {
                    ret.append(", ");
                }
            }
        });

        return ret.toString();
    }

    public String statesWithBiggestSales() {
        List<Entry> sales = repository.getEntries();
        StringBuilder ret = new StringBuilder();
        Map<String, Double> statesAmount = new HashMap<>();
//        sales.subList(1, sales.size()).stream()
//                .filter(entry -> entry.getState().equals("California"))
//                .map(entry -> entry.getAmount())
//                .forEach(amount -> {
//                    sum += amount;
//                });
//        System.out.println("California: " + sum);
//        sum = 0.0;
//
//        sales.subList(1, sales.size()).stream()
//                .filter(entry -> entry.getState().equals("Texas"))
//                .map(entry -> entry.getAmount())
//                .forEach(amount -> {
//                    sum += amount;
//                });
//        System.out.println("Texas: " + sum);
//        sum = 0.0;
//
//        sales.subList(1, sales.size()).stream()
//                .filter(entry -> entry.getState().equals("New York"))
//                .map(entry -> entry.getAmount())
//                .forEach(amount -> {
//                    sum += amount;
//                });
//        System.out.println("New York: " + sum);
//        sum = 0.0;
//
//
//        sales.subList(1, sales.size()).stream()
//                .filter(entry -> entry.getState().equals("North Carolina"))
//                .map(entry -> entry.getAmount())
//                .forEach(amount -> {
//                    sum += amount;
//                });
//        System.out.println("North Carolina: " + sum);
//        sum = 0.0;
//        sales.subList(1, sales.size()).stream()
//                .filter(entry -> entry.getState().equals("Georgia"))
//                .map(entry -> entry.getAmount())
//                .forEach(amount -> {
//                    sum += amount;
//                });
//        System.out.println("Georgia: " + sum);
//        sum = 0.0;

        for (Entry entry : sales.subList(1, sales.size())) {
            String state = entry.getState();
            if (statesAmount.containsKey(state)) {
                sum = statesAmount.get(state) + entry.getAmount();
                statesAmount.replace(state, sum);
            } else {
                statesAmount.put(state, 0.0);
            }
        }

        List<Map.Entry<String, Double>> list = new LinkedList<>(statesAmount.entrySet());
        list.sort(Map.Entry.comparingByValue());
        Collections.reverse(list);

        list.forEach(elem -> {
            if (count != 3) {
                count++;
                ret.append(elem.getKey());
                if (count != 3) {
                    ret.append(", ");
                }
            }
        });

        return ret.toString();
    }
}
