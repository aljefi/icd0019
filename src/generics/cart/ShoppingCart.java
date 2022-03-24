package generics.cart;

import java.util.*;

public class ShoppingCart<T extends CartItem> {

    Map<String, List> map = new HashMap<>();
    ArrayList disc = new ArrayList();

    public void add(Object item) {
        CartItem cart = (CartItem) item;
        List temp = new ArrayList();

        if (map.containsKey(cart.getId())) {
            increaseQuantity(cart.getId());
        } else {
            temp.add(cart.getPrice());
            temp.add(1.0);

            map.put(cart.getId(), temp);
        }
    }

    public void removeById(String id) {
        map.remove(id);
    }

    public Double getTotal() {
        double sum = 0.0;
        for (List elem : map.values()) {
            double temp = (double) elem.get(0) * (double) elem.get(1);
            sum += temp;
        }
        for (Object discount : disc) {
            sum = sum * (100 - (double) discount) / 100;
        }
        return sum;
    }

    public void increaseQuantity(String id) {
        List temp = new ArrayList();

        temp.add(map.get(id).get(0));
        temp.add((double) map.get(id).get(1) + 1.0);

        map.replace(id, temp);
    }

    public void applyDiscountPercentage(Double discount) {
        disc.add(discount);
    }

    public void removeLastDiscount() {
        disc.remove(disc.size() - 1);
    }

    public void addAll(Object items) {
        List<CartItem> carts = (List<CartItem>) items;
        for (CartItem cart : carts) {
            add(cart);
        }
    }

    @Override
    public String toString() {

        Object[] a = map.keySet().toArray();

        Collection<List> b = map.values();

        StringBuilder ret = new StringBuilder();

        double price;
        double quantity;
        int i = 0;

        for (List o : b) {
            price = (double) o.get(0);
            quantity = (double) o.get(1);

            ret.append("(");
            ret.append(a[i]);

            ret.append(", ");
            ret.append(price);

            ret.append(", ");
            ret.append((int) quantity);

            if (i + 1 == map.keySet().size()) {
                ret.append(")");
            } else {
                ret.append("), ");
            }
            i++;
        }

        return ret.toString();
    }
}
