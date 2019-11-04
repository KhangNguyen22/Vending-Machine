import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DrinksTest {

    @Test
    public void testWaterPrice() {
        assertEquals(10, Drinks.Water.getPrice(), 0.1);
    }

    @Test
    public void testSoftDrinkPrice() {
        assertEquals(10, Drinks.SoftDrink.getPrice(), 0.1);
    }

    @Test
    public void testJuicePirce() {
        assertEquals(10, Drinks.Juice.getPrice(), 0.1);
    }

    @Test
    public void testJuiceSetPrice() {
        Drinks.Juice.setPrice(14.0);
        assertEquals(14, Drinks.Juice.getPrice(), 0.1);
        Drinks.Juice.setPrice(10.0);
    }

    @Test
    public void testWaterSetPrice() {
        Drinks.Water.setPrice(15.0);
        assertEquals(15, Drinks.Water.getPrice(), 0.1);
        Drinks.Water.setPrice(10.0);
    }

    @Test
    public void testSoftDrinkSetPrice() {
        Drinks.SoftDrink.setPrice(14.0);
        assertEquals(14, Drinks.SoftDrink.getPrice(), 0.1);
        Drinks.SoftDrink.setPrice(10.0);
    }
}
