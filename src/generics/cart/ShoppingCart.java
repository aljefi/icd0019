package generics.cart;

import java.util.*;

public class ShoppingCart<T extends CartItem> {
    private final LinkedList<T> cart = new LinkedList<>();
    private final List<Double> discounts = new ArrayList<>();

    public void add(T item) {
        cart.add(item);
    }

    public void removeById(String id) {
        for (T item : cart) {
            if (item.getId().equals(id)) {
                cart.remove(item);
                break;
            }
        }
    }

    public Double getTotal() {
        double total = 0D;
        for (T item : cart) {
            total += item.getPrice();
        }
        for (Double discount : discounts) {
            total *= (100 - discount) / 100;
        }

        return total;
    }

    public void increaseQuantity(String id) {
        for (T item : cart) {
            if (item.getId().equals(id)) {
                add(item);
                break;
            }
        }
    }

    public void applyDiscountPercentage(Double discount) {
        discounts.add(discount);
    }

    public void removeLastDiscount() {
        discounts.remove(discounts.size() - 1);
    }

    public void addAll(Collection<T> items) {
        for (T item : items) {
            add(item);
        }
    }

    @Override
    public String toString() {
        HashMap<String, Integer> map = new HashMap<>();

        for (T item : cart) {
            map.compute(item.getId(), (k, v) -> (v == null) ? 1 : (v + 1));
        }

        StringBuilder sb = new StringBuilder();
        for (T item : cart) {

            if (!map.containsKey(item.getId())) {
                continue;
            }
            String s = "(" + item.getId() + ", " + item.getPrice() + ", " + map.get(item.getId()) + ")";

            map.remove(item.getId());
            if (!map.keySet().isEmpty()) {
                s += ", ";
            }

            sb.append(s);
        }

        return sb.toString();
    }
}
