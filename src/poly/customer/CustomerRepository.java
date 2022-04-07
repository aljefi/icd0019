package poly.customer;

import java.io.*;
import java.time.LocalDate;
import java.util.*;

public class CustomerRepository {

    private static final String FILE_PATH = "src/poly/customer/data.txt";

    private List<AbstractCustomer> customers = new ArrayList<>();

    public Optional<AbstractCustomer> getCustomerById(String id) {
        File myObj = new File(FILE_PATH);
        Scanner myReader = null;
        try {
            myReader = new Scanner(myObj);
        } catch (FileNotFoundException ignored) {
        }
        while (myReader != null && myReader.hasNextLine()) {
            String data = myReader.nextLine();
            String[] temp = data.split(";");
            if (temp[0].equals("REGULAR")) {
                String customerId = temp[1];
                String name = temp[2];
                int bonusPoints = Integer.parseInt(temp[3]);
                LocalDate lastOrderDate = LocalDate.parse(temp[4]);
                customers.add(new RegularCustomer(customerId, name, bonusPoints, lastOrderDate));
            } else if (temp[0].equals("GOLD")) {
                String customerId = temp[1];
                String name = temp[2];
                int bonusPoints = Integer.parseInt(temp[3]);
                customers.add(new GoldCustomer(customerId, name, bonusPoints));
            }
        }

        for (AbstractCustomer customer : customers) {
            if (customer.id.equals(id)) {
                return Optional.of(customer);
            }
        }
        return Optional.empty();
    }

    public void remove(String id) {
        for (int i = 0; i < customers.size(); i++) {
            AbstractCustomer o = customers.get(i);
            if (id.equals(o.getId())) {
                customers.remove(i);
                break;
            }
        }
    }

    public void save(AbstractCustomer customer) {
        if (!customers.contains(customer)) {
            customers.add(customer);
        }

        StringBuilder input = new StringBuilder();
        File myObj = new File(FILE_PATH);
        Scanner myReader = null;
        try {
            myReader = new Scanner(myObj);
        } catch (FileNotFoundException ignored) {
        }
        while (myReader != null && myReader.hasNextLine()) {
            helpMethod(myReader, customer, input);
        }
        FileOutputStream fileOut;
        try {
            fileOut = new FileOutputStream(FILE_PATH);
            fileOut.write(input.toString().getBytes());
        } catch (IOException ignored) {
        }
    }

    public void helpMethod(Scanner myReader, AbstractCustomer customer, StringBuilder input) {
        String data = myReader.nextLine();
        String[] temp = data.split(";");

        BufferedReader file = null;
        try {
            file = new BufferedReader(new FileReader(FILE_PATH));
        } catch (FileNotFoundException ignored) {
        }

        String line;

        while (true) {
            try {
                if (file.readLine() == null) {
                    break;
                }
            } catch (IOException ignored) {
            }

            if (temp[1].equals(customer.id)) {
                temp[3] = String.valueOf(customer.bonusPoints);
            }

            StringBuilder s = new StringBuilder();
            s.append(temp[0]);
            s.append(";");
            s.append(temp[1]);
            s.append(";");
            s.append(temp[2]);
            s.append(";");
            s.append(temp[3]);
            s.append(";");
            if (temp[0].equals("REGULAR")) {
                s.append(temp[4]);
            }
            line = s.toString();
            input.append(line);
            input.append('\n');
            break;
        }
    }

    public int getCustomerCount() {
        return customers.size();
    }
}

//REGULAR;c1;Alice;0;2021-03-10
//REGULAR;c2;Bob;0;2021-01-04
//GOLD;c3;Carol;0;
