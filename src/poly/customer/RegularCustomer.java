package poly.customer;

import java.time.LocalDate;
import java.time.Period;

public final class RegularCustomer extends AbstractCustomer {

    LocalDate date;


    public RegularCustomer(String id, String name,
                           int bonusPoints, LocalDate lastOrderDate) {

        super(id, name, bonusPoints);

        this.id = super.getId();
        this.name = super.getName();
        this.bonusPoints = super.getBonusPoints();
        date = lastOrderDate;

    }

    @Override
    public void collectBonusPointsFrom(Order order) {
        if (order.getTotal() >= 100) {
            double days = Period.between(order.getDate(), date).getDays();
            System.out.println("days: " + days);
            if (days > -20) {
                bonusPoints += order.getTotal() * 1.5;
            } else{
                bonusPoints += order.getTotal();
            }

        }
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj;
    }

    @Override
    public int hashCode() {
        throw new RuntimeException("not implemented yet");
    }

    @Override
    public String asString() {
        String ret = id +
                ", " +
                name +
                ", " +
                bonusPoints;
        return ret;
    }

}