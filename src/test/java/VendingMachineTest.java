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

    @Test
    public void testInvalidHandleLogin() {
        System.setIn(new ByteArrayInputStream("iuoiudwojwi\nwuiuowiuo\n".getBytes()));
        VendingMachine vendingMachine = new VendingMachine(10);
        vendingMachine.handleLogin();
        assertEquals("Please enter your username.\nPlease enter your password.\nUnsuccessful login.\n", baos.toString());
    }



    @Test
    public void testAlreadyLoggedIn() {
        System.setIn(new ByteArrayInputStream("beefsupreme\nhunter2".getBytes()));
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

}
