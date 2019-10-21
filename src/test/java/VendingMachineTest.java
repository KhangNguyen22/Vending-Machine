import org.junit.*;
import static org.junit.Assert.*;


public class VendingMachineTest {

    @Before
    public void setUp() {
        VendingMachine vendingMachine = new VendingMachine(10);
    }

    // Assert that the program returns after receiving arguments
    @Test
    public void testVendingMachineMultipleArguments() {
        VendingMachine.main(new String[]{"test1"});

    }

    @Test
    public void testListPayments() {
        String expected = "1. $20\n" +
                "2. $10\n" +
                "3. $5\n" +
                "4. $2\n" +
                "5. $1\n" +
                "6. 50c\n" +
                "7. 20c\n" +
                "8. 10c\n";
        assertTrue("", expected.equals(vendingMachine.listPayments()));
    }

}
