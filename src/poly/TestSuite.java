package poly;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import poly.customer.CustomerTest;
import poly.customer.RepositoryTest;
import poly.fruit.FruitTest;
import poly.shapes.ShapeTest;
import poly.undo.CalculatorTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        FruitTest.class,
        ShapeTest.class,
        CalculatorTest.class,
        AverageTest.class,
        RepositoryTest.class,
        CustomerTest.class})
public class TestSuite {

}