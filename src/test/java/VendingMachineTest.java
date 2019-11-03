import org.junit.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class VendingMachineTest {
    private VendingMachine vendingMachine = null;
    private final InputStream origStream = System.in;
    private final PrintStream origOutput = System.out;

    private final ByteArrayOutputStream baos = new ByteArrayOutputStream();


    @Before
    public void setUp() {
        vendingMachine = new VendingMachine(10);
        System.setOut(new PrintStream(baos));
    }

    @After
    public void tearDown() {
        vendingMachine = null;
        System.setIn(origStream);
        System.setOut(origOutput);
    }

    // Assert that the program returns after receiving arguments
    @Test
    public void testVendingMachineMultipleArguments() {
        VendingMachine.main(new String[]{"test1"});

    }

    @Test
    public void testListTransactions() {
        String expected = "1. Add Item\n" + "2. Remove Item\n" + "3. Finalise Transaction\n" + "4. Discard Transaction and Exit\n";
        assertEquals(expected, vendingMachine.listTransactionOptions());
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
                "8. 10c\n" +
                "99. QUIT";
        assertTrue("", expected.equals(vendingMachine.listPayments()));
    }

    @Test
    public void testInvalidHandleLogin() {
        System.setIn(new ByteArrayInputStream("iuoiudwojwi\nwuiuowiuo\n".getBytes()));
        VendingMachine vendingMachine = new VendingMachine(10);
        vendingMachine.handleLogin();
        assertEquals("Please enter your username.\nPlease enter your password.\nUnsuccessful login.\n", baos.toString());
    }



    @Test
    public void testAlreadyLoggedIn() {
        System.setIn(new ByteArrayInputStream("beefsupreme\nhunter2\n".getBytes()));
        VendingMachine vendingMachine = new VendingMachine(10);
        vendingMachine.handleLogin();
        vendingMachine.handleLogin();
        assertEquals("Please enter your username.\nPlease enter your password.\nSuccessfully Logged in.\nAlready logged in\n", baos.toString());
    }


    @Test
    public void testValidLoggedIn() {
        System.setIn(new ByteArrayInputStream("beefsupreme\nhunter2".getBytes()));
        VendingMachine vendingMachine = new VendingMachine(10);
        vendingMachine.handleLogin();
        assertEquals("Please enter your username.\nPlease enter your password.\nSuccessfully Logged in.\n", baos.toString());
    }

    @Test
    public void testListStaffOptions() {
        assertEquals("1. Fill\n" +
                "2. View Daily Transactions\n" +
                "3. View Cancelled Transactions\n" +
                "4. Change Product Prices\n" +
                "5. Add staff\n" +
                "6. Logout\n", new VendingMachine(10).listStaffOptions());
    }

    @Test
    public void testFillPrint() {
        VendingMachine vendingMachine = new VendingMachine(10);
        vendingMachine.fill();
        assertEquals("Vending machine has been filled.\n", baos.toString());
    }

    @Test
    public void testHandleStaffOptionsNonAdmin() {
        VendingMachine vendingMachine = new VendingMachine(10);
        vendingMachine.handleStaffOptions();
        assertEquals("You are not logged in as a staff user.\n", baos.toString());
    }

    @Test
    public void testChangeProductPriceNegativeID() {
        String result = ("Please select the id of the snack you want to change the price for.\n" +
                vendingMachine.listItems() +
                "\nInvalid item\n");
        System.setIn(new ByteArrayInputStream("-1\n".getBytes()));
        VendingMachine vendingMachine = new VendingMachine(10);
        vendingMachine.changeProductPrice();

        assertEquals(result, baos.toString());
    }


    @Test
    public void testChangeProductPriceInvalidPrice() {
        String result = ("Please select the id of the snack you want to change the price for.\n" +
                vendingMachine.listItems() +
                "\nWhat would you like the new price to be?\n" + "Invalid price.\n");
        System.setIn(new ByteArrayInputStream("1\nijijiuj".getBytes()));
        VendingMachine vendingMachine = new VendingMachine(10);
        vendingMachine.changeProductPrice();

        assertEquals(result, baos.toString());
    }

    @Test
    public void testChangeProductPriceNegativePrice() {
        String result = ("Please select the id of the snack you want to change the price for.\n" +
                vendingMachine.listItems() +
                "\nWhat would you like the new price to be?\n" + "Invalid price.\n");
        System.setIn(new ByteArrayInputStream("1\n-2".getBytes()));
        VendingMachine vendingMachine = new VendingMachine(10);
        vendingMachine.changeProductPrice();

        assertEquals(result, baos.toString());
    }


    @Test
    public void testChangeProductPriceValidPrintMessage() {
        String result = ("Please select the id of the snack you want to change the price for.\n" +
                vendingMachine.listItems() +
                "\nWhat would you like the new price to be?\n" + "Snack price has been updated.\n");
        System.setIn(new ByteArrayInputStream("1\n10".getBytes()));
        VendingMachine vendingMachine = new VendingMachine(10);
        vendingMachine.changeProductPrice();

        assertEquals(result, baos.toString());
    }


    @Test
    public void testChangeProductPriceValidProductName() {
        String result = ("Please select the id of the snack you want to change the price for.\n" +
                vendingMachine.listItems() +
                "\nWhat would you like the new price to be?\n" + "Snack price has been updated.\n");
        System.setIn(new ByteArrayInputStream("softdrink\n10".getBytes()));
        VendingMachine vendingMachine = new VendingMachine(10);
        vendingMachine.changeProductPrice();

        assertEquals(result, baos.toString());
    }

    @Test
    public void testMainWelcomeMessageAndOptionsList() {
        String result = "Welcome to the Snack & Co. Vending Machine. Hope you enjoy the experience!\n\n" +
                "Please enter the number corresponding to the option you want.\n\n" +
                vendingMachine.listOptions() + "\n";

        System.setIn(new ByteArrayInputStream("".getBytes()));
        try {
            VendingMachine.main(new String[]{});
        } catch (Exception ignored) {

        }

        assertEquals(result, baos.toString());
    }


    @Test
    public void testMainInvalidOptionSelection() {
        String result = "Welcome to the Snack & Co. Vending Machine. Hope you enjoy the experience!\n\n" +
                "Please enter the number corresponding to the option you want.\n\n" +
                vendingMachine.listOptions() + "\nInvalid, please enter a number.\n" +
                "Please enter the number corresponding to the option you want.\n\n" +
                vendingMachine.listOptions() + "\n";

        System.setIn(new ByteArrayInputStream("jhuhiuhuii\n".getBytes()));
        try {
            VendingMachine.main(new String[]{});
        } catch (Exception ignored) {

        }

        assertEquals(result, baos.toString());
    }



}
