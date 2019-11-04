import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ChipsTest {

    @Test
    public void testOriginalPrice() {
        assertEquals(10, Chips.Original.getPrice(), 0.1);
    }

    @Test
    public void testChickenPrice() {
        assertEquals(10, Chips.Chicken.getPrice(), 0.1);
    }

    @Test
    public void testBBQPrice() {
        assertEquals(14, Chips.BBQ.getPrice(), 0.1);
    }

    @Test
    public void testSweetChilliPrice() {
        assertEquals(10, Chips.SweetChilli.getPrice(), 0.1);
    }

    @Test
    public void testSetPriceOriginal() {
        Chips.Original.setPrice(20.0);
        assertEquals(20, Chips.Original.getPrice(), 0.1);
        Chips.Original.setPrice(10.0);
    }

}
