import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SnackAndQuantityTest {

    @Test
    public void testGetSnack() {
        SnackAndQuantity snackAndQuantity = new SnackAndQuantity(Chips.BBQ, 10);
        assertEquals(Chips.BBQ, snackAndQuantity.getSnack());
    }

    @Test
    public void testDecrementQuantity() {
        SnackAndQuantity snackAndQuantity = new SnackAndQuantity(Chips.Chicken, 10);
        snackAndQuantity.decrementQuantityBy(8);
        assertEquals(2, snackAndQuantity.quantity);
    }

    @Test
    public void testIncrementQuantity() {
        SnackAndQuantity snackAndQuantity = new SnackAndQuantity(Chips.Original, 10);
        snackAndQuantity.incrementQuantityBy(4);
        assertEquals(14, snackAndQuantity.quantity);
    }

}
