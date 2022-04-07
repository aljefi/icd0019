package poly.customer;

public final class GoldCustomer extends AbstractCustomer {

    public GoldCustomer(String id, String name, int bonusPoints) {
        super(id, name, bonusPoints);

        this.id = super.getId();
        this.name = super.getName();
        this.bonusPoints = super.getBonusPoints();
    }

    @Override
    public void collectBonusPointsFrom(Order order) {
        if (order.getTotal() >= 100) {
            bonusPoints += order.getTotal() * 1.5;
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