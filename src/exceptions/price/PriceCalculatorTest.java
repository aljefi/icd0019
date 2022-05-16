package exceptions.price;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.Matchers.is;

public class PriceCalculatorTest {

    @Test
    public void ifFileIsReadableThenPriceIsCalculated() {
        MyFileResource.setData(2); // simulates that file contains number 2

        double price = new PriceCalculator().calculatePrice();

        assertThat(price, is(closeTo(144, 0)));
    }

    @Test
    public void ifFileIsNotReadableThenPriceIsNotCalculated() {
        MyFileResource.setData(-1); // simulates read error

        double price = new PriceCalculator().calculatePrice();

        assertThat(price, is(closeTo(-1, 0)));
    }

    @Test
    public void ifFileIsNotReadableThenThrowException() {
        MyFileResource.makeItThrowOnRead(); // simulates exception on reading

        double price = new PriceCalculator().calculatePrice();

        assertThat(price, is(closeTo(-1, 0)));
    }

}
