import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ChocolatesTest {

    @Test
    public void testBountyPrice() {
        assertEquals(10, Chocolates.Bounty.getPrice(), 0.1);
    }

    @Test
    public void testMarsPrice() {
        assertEquals(10, Chocolates.Mars.getPrice(), 0.1);
    }

    @Test
    public void testMChocolatePrice() {
        assertEquals(10, Chocolates.MChocolate.getPrice(), 0.1);
    }

    @Test
    public void testSneakersPrice() {
        assertEquals(10, Chocolates.Sneakers.getPrice(), 0.1);
    }

    @Test
    public void testBountySetPrice() {
        Chocolates.Bounty.setPrice(20.0);
        assertEquals(20.0, Chocolates.Bounty.getPrice(), 0.1);
        Chocolates.Bounty.setPrice(10.0);
    }
}
