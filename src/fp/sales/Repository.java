package fp.sales;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Repository {

    private static final String FILE_PATH = "src/fp/sales/sales-data.csv";

    private DateTimeFormatter formatter = DateTimeFormatter
            .ofPattern("dd.MM.yyyy");

    public List<Entry> getEntries() {
        List<Entry> list = new ArrayList<>();
        List<String> temp;

        try {
            temp = Files.readAllLines(Paths.get(FILE_PATH));
            temp.stream().map(s -> s.split("\t")).forEach(a -> {
//                        System.out.println(a[0]);
//                        System.out.println(a[1]);
//                        System.out.println(a[2]);
//                        System.out.println(a[3]);
//                        System.out.println(a[4]);
//                        System.out.println(a[5]);
//                        System.out.println();
                        Entry entry = new Entry();
                        if (!a[0].startsWith("Order Date")) {
                            entry.setDate(LocalDate.parse(a[0], formatter));
                        }
                        if (!a[1].startsWith("State")) {
                            entry.setState(a[1]);
                        }
                        if (!a[2].startsWith("Product ID")) {
                            entry.setProductId(a[2]);
                        }
                        if (!a[3].startsWith("Category")) {
                            entry.setCategory(a[3]);
                        }
                        if (!a[4].startsWith("Product Name")) {
//                            entry.set???
                        }
                        if (!a[5].startsWith("Sales")) {
                            double amount = Double.parseDouble(a[5].replace(",", "."));
                            entry.setAmount(amount);
                        }
                        list.add(entry);
                    }
            );
        } catch (IOException e) {
            e.printStackTrace();
        }

//        System.out.println(list.get(1).getDate());
//        System.out.println(list.get(1).getState());
//        System.out.println(list.get(1).getProductId());
//        System.out.println(list.get(1).getCategory());
//        System.out.println(list.get(1).getAmount());

        return list;
    }

}
