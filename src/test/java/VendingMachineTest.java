import org.junit.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import static org.junit.Assert.*;


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
        String expected = "1. Add Item\n" + "2. Finalise Transaction\n" + "3. Discard Transaction and Exit\n";
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
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy 'at' HH:mm:ss z");
        Date date = new Date(System.currentTimeMillis());
        String result = "Vending machine has been filled on the date " + formatter.format(date);

        assertEquals("Success!! \n" + result +"\n", baos.toString());
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

    @Test
    public void testAddTransactionsInvalidItemSelected(){

        String result = ("Please select the id of the snack you want to purchase.\n" +
        vendingMachine.listItems() +
        "\nInvalid item\n"); 
        System.setIn(new ByteArrayInputStream("71\n".getBytes()));
        VendingMachine vendingMachine = new VendingMachine(10);
        Transaction tran = vendingMachine.initialiseNewTransaction();
        vendingMachine.addProductToTransaction(tran);
        assertEquals(result,baos.toString());
    }

    @Test
    public void testAddTransactionsNegativeAmountSelected(){

        String result = ("Please select the id of the snack you want to purchase.\n" +
        vendingMachine.listItems() +
        "\nHow many would you like to purchase?\n" + "Invalid amount.\n"); 
        System.setIn(new ByteArrayInputStream("1\n-1".getBytes()));
        VendingMachine vendingMachine = new VendingMachine(10);
        Transaction tran = vendingMachine.initialiseNewTransaction();
        vendingMachine.addProductToTransaction(tran);
        assertEquals(result,baos.toString());
    }

    @Test
    public void testAddTransactionsAskTooMuch(){

        String result = ("Please select the id of the snack you want to purchase.\n" +
        vendingMachine.listItems() +
        "\nHow many would you like to purchase?\n" + "Not enough stock.\n"); 
        System.setIn(new ByteArrayInputStream("1\n1000".getBytes()));
        VendingMachine vendingMachine = new VendingMachine(10);
        Transaction tran = vendingMachine.initialiseNewTransaction();
        vendingMachine.addProductToTransaction(tran);
        assertEquals(result,baos.toString());
    }

    @Test
    public void testAddTransactionsValidAmountAsked(){

        String result = ("Please select the id of the snack you want to purchase.\n" +
        vendingMachine.listItems() +
        "\nHow many would you like to purchase?\n" + "Adding to transaction\n"); 
        System.setIn(new ByteArrayInputStream("1\n2".getBytes()));
        VendingMachine vendingMachine = new VendingMachine(10);
        Transaction tran = vendingMachine.initialiseNewTransaction();
        vendingMachine.addProductToTransaction(tran);
        assertEquals(result,baos.toString());
    }

    @Test
    public void testPrintDailyTransactionsEmpty() {
        vendingMachine.printDailyTransactions();
        assertEquals("", baos.toString());
    }

    @Test
    public void testPrintCancelledTransactionsEmpt() {
        vendingMachine.printCancelledTransactions();
        assertEquals("*** CANCELLED TRANSACTIONS ***\nNO CANCELLED TRANSACTIONS TO DISPLAY\n", baos.toString());
    }

    @Test
    public void testFinaliseTransaction() {

        System.setIn(new ByteArrayInputStream("1\n".getBytes()));

        VendingMachine vendingMachine = new VendingMachine(10);

        Transaction transaction = new VendingTransaction(1);
        transaction.addProductToTransaction(1, Drinks.Juice, 1);
        vendingMachine.finaliseTransaction(transaction);

        String result = "***FINALISING TRANSACTION***\n\n***Transaction Summary: \n\n\n" +
                transaction.getTransactionSummary() +
                "\nThe total for this transaction is: " + 10.0 +
                "\nThe amount you have to pay is:  $" + 10.0 +
                "\nPlease enter the amount you are going to pay: \n" +
                vendingMachine.listPayments() +
                "\nChange due: 10.0" +
                "\nTransaction has been finalised.\n";
        assertEquals(result, baos.toString());
    }


    @Test
    public void testFinaliseTransactionQuit() {

        System.setIn(new ByteArrayInputStream("99\n".getBytes()));

        VendingMachine vendingMachine = new VendingMachine(10);

        Transaction transaction = new VendingTransaction(1);
        transaction.addProductToTransaction(1, Drinks.Juice, 1);

        String result = "***FINALISING TRANSACTION***\n\n***Transaction Summary: \n\n\n" +
                transaction.getTransactionSummary() +
                "\nThe total for this transaction is: " + 10.0 +
                "\nThe amount you have to pay is:  $" + 10.0 +
                "\nPlease enter the amount you are going to pay: \n" +
                vendingMachine.listPayments() +
                "\nQuitting transaction.\n" +
                "Refunding amount ....... $0.0\n";
        vendingMachine.finaliseTransaction(transaction);
        assertEquals(result, baos.toString());
    }


    @Test
    public void testFinaliseTransactionInvalid() {

        System.setIn(new ByteArrayInputStream("-1\n1".getBytes()));

        VendingMachine vendingMachine = new VendingMachine(10);

        Transaction transaction = new VendingTransaction(1);
        transaction.addProductToTransaction(1, Drinks.Juice, 1);

        String result = "***FINALISING TRANSACTION***\n\n***Transaction Summary: \n\n\n" +
                transaction.getTransactionSummary() +
                "\nThe total for this transaction is: " + 10.0 +
                "\nThe amount you have to pay is:  $" + 10.0 +
                "\nPlease enter the amount you are going to pay: \n" +
                vendingMachine.listPayments() +
                "\n\nInvalid option." +
                "\nThe amount you have to pay is:  $" + 10.0 +
                "\nPlease enter the amount you are going to pay: \n" +
                vendingMachine.listPayments() +
                "\nChange due: 10.0" +
                "\nTransaction has been finalised.\n";

        vendingMachine.finaliseTransaction(transaction);
        assertEquals(result, baos.toString());
    }


    @Test
    public void testHandleStartOption() {
        System.setIn(new ByteArrayInputStream("beefsupreme\nhunter2\n2\n".getBytes()));
        VendingMachine vendingMachine = new VendingMachine(10);
        vendingMachine.handleLogin();
        vendingMachine.handleStaffOptions();

        assertEquals("Please enter your username.\n" +
                "Please enter your password.\n" +
                "Successfully Logged in.\n" +
                "\n" +
                "Staff Options\n" +
                "\n" +
                "1. Fill\n" +
                "2. View Daily Transactions\n" +
                "3. View Cancelled Transactions\n" +
                "4. Change Product Prices\n" +
                "5. Add staff\n" +
                "6. Logout\n\n", baos.toString());
    }

    @Test
    public void testHandleStartOption2() {
        System.setIn(new ByteArrayInputStream("beefsupreme\nhunter2\n3\n".getBytes()));
        VendingMachine vendingMachine = new VendingMachine(10);
        vendingMachine.handleLogin();
        vendingMachine.handleStaffOptions();

        assertEquals("Please enter your username.\n" +
                "Please enter your password.\n" +
                "Successfully Logged in.\n" +
                "\n" +
                "Staff Options\n" +
                "\n" +
                "1. Fill\n" +
                "2. View Daily Transactions\n" +
                "3. View Cancelled Transactions\n" +
                "4. Change Product Prices\n" +
                "5. Add staff\n" +
                "6. Logout\n\n" +
                "*** CANCELLED TRANSACTIONS ***\n" +
                "NO CANCELLED TRANSACTIONS TO DISPLAY\n", baos.toString());
    }


    @Test
    public void testHandleStartOption3() {
        System.setIn(new ByteArrayInputStream("beefsupreme\nhunter2\n4\n1\n10\n".getBytes()));
        VendingMachine vendingMachine = new VendingMachine(10);
        vendingMachine.handleLogin();
        vendingMachine.handleStaffOptions();

        assertEquals("Please enter your username.\n" +
                "Please enter your password.\n" +
                "Successfully Logged in.\n" +
                "\n" +
                "Staff Options\n" +
                "\n" +
                "1. Fill\n" +
                "2. View Daily Transactions\n" +
                "3. View Cancelled Transactions\n" +
                "4. Change Product Prices\n" +
                "5. Add staff\n" +
                "6. Logout\n\nPlease select the id of the snack you want to change the price for.\n" +
                "\n" +
                "Snack -- Price -- Stock\n" +
                "1: SoftDrink | Price: 10.0 | Stock: 10\n" +
                "2: Juice | Price: 10.0 | Stock: 10\n" +
                "3: Water | Price: 10.0 | Stock: 10\n" +
                "4: MChocolate | Price: 10.0 | Stock: 10\n" +
                "5: Bounty | Price: 10.0 | Stock: 10\n" +
                "6: Mars | Price: 10.0 | Stock: 10\n" +
                "7: Sneakers | Price: 10.0 | Stock: 10\n" +
                "8: Original | Price: 10.0 | Stock: 10\n" +
                "9: Chicken | Price: 10.0 | Stock: 10\n" +
                "10: BBQ | Price: 14.0 | Stock: 10\n" +
                "11: SweetChilli | Price: 10.0 | Stock: 10\n" +
                "12: SourWorms | Price: 2.0 | Stock: 10\n" +
                "13: JellyBeans | Price: 2.5 | Stock: 10\n" +
                "14: LittleBears | Price: 1.6 | Stock: 10\n" +
                "15: PartMix | Price: 1.5 | Stock: 10\n" +
                "\n" +
                "What would you like the new price to be?\n" +
                "Snack price has been updated.\n", baos.toString());
    }

    @Test
    public void testHandleStartOption4() {
        System.setIn(new ByteArrayInputStream("beefsupreme\nhunter2\n1\n".getBytes()));
        VendingMachine vendingMachine = new VendingMachine(10);
        vendingMachine.handleLogin();
        vendingMachine.handleStaffOptions();


    }

    @Test
    public void testHandleStartOptionAdd() {
        System.setIn(new ByteArrayInputStream("beefsupreme\nhunter2\n5\nuser\n2\n1\n".getBytes()));
        VendingMachine vendingMachine = new VendingMachine(10);
        vendingMachine.handleLogin();
        vendingMachine.handleStaffOptions();

        assertEquals("Please enter your username.\n" +
                "Please enter your password.\n" +
                "Successfully Logged in.\n" +
                "\n" +
                "Staff Options\n" +
                "\n" +
                "1. Fill\n" +
                "2. View Daily Transactions\n" +
                "3. View Cancelled Transactions\n" +
                "4. Change Product Prices\n" +
                "5. Add staff\n" +
                "6. Logout\n" +
                "\n" +
                "Enter the desired username.\n" +
                "Enter the desired password.\n" +
                "Please select the option you want.\n" +
                "\n" +
                "1. Add as Staff\n" +
                "2. Add as Superuser\n" +
                "\n" +
                "Successfully added user.\n", baos.toString());
    }

    @Test
    public void testHandleStartOptionAdd2() {
        System.setIn(new ByteArrayInputStream("beefsupreme\nhunter2\n5\nuser\n2\n2\n".getBytes()));
        VendingMachine vendingMachine = new VendingMachine(10);
        vendingMachine.handleLogin();
        vendingMachine.handleStaffOptions();

        assertEquals("Please enter your username.\n" +
                "Please enter your password.\n" +
                "Successfully Logged in.\n" +
                "\n" +
                "Staff Options\n" +
                "\n" +
                "1. Fill\n" +
                "2. View Daily Transactions\n" +
                "3. View Cancelled Transactions\n" +
                "4. Change Product Prices\n" +
                "5. Add staff\n" +
                "6. Logout\n" +
                "\n" +
                "Enter the desired username.\n" +
                "Enter the desired password.\n" +
                "Please select the option you want.\n" +
                "\n" +
                "1. Add as Staff\n" +
                "2. Add as Superuser\n" +
                "\n" +
                "Successfully added user.\n", baos.toString());
    }

    @Test
    public void testHandleStartOptionAdd3() {
        System.setIn(new ByteArrayInputStream("beefsupreme\nhunter2\n5\nuser\n2\n3\n".getBytes()));
        VendingMachine vendingMachine = new VendingMachine(10);
        vendingMachine.handleLogin();
        vendingMachine.handleStaffOptions();

        assertEquals("Please enter your username.\n" +
                "Please enter your password.\n" +
                "Successfully Logged in.\n" +
                "\n" +
                "Staff Options\n" +
                "\n" +
                "1. Fill\n" +
                "2. View Daily Transactions\n" +
                "3. View Cancelled Transactions\n" +
                "4. Change Product Prices\n" +
                "5. Add staff\n" +
                "6. Logout\n" +
                "\n" +
                "Enter the desired username.\n" +
                "Enter the desired password.\n" +
                "Please select the option you want.\n" +
                "\n" +
                "1. Add as Staff\n" +
                "2. Add as Superuser\n" +
                "\n" +
                "Invalid option. Exiting menu\n", baos.toString());
    }

    @Test
    public void testHandleStartOptionAddNotStaff() {
        System.setIn(new ByteArrayInputStream("beefsupreme\nhunter2\n5\nuser\n2\n1\n".getBytes()));
        VendingMachine vendingMachine = new VendingMachine(10);
        vendingMachine.addStaff();

        assertEquals("Only superuser can create staff.\n", baos.toString());
    }

    @Test
    public void testHandleStartOptionAddAlreadyStaff() {
        System.setIn(new ByteArrayInputStream("beefsupreme\nhunter2\n5\nbeefsupreme\n2\n1\n".getBytes()));
        VendingMachine vendingMachine = new VendingMachine(10);
        vendingMachine.handleLogin();
        vendingMachine.handleStaffOptions();

        assertEquals("Please enter your username.\n" +
                "Please enter your password.\n" +
                "Successfully Logged in.\n" +
                "\n" +
                "Staff Options\n" +
                "\n" +
                "1. Fill\n" +
                "2. View Daily Transactions\n" +
                "3. View Cancelled Transactions\n" +
                "4. Change Product Prices\n" +
                "5. Add staff\n" +
                "6. Logout\n" +
                "\n" +
                "Enter the desired username.\n" +
                "Username already exists. Exiting menu.\n", baos.toString());
    }

    @Test
    public void vendingMachineStart() {

        System.setIn(new ByteArrayInputStream("3\n".getBytes()));
        try {
            VendingMachine.main(new String[]{});
        } catch (Exception e) {

        }

    }

    @Test
    public void testValidateIncorrect() {

        VendingMachine vendingMachine = new VendingMachine(10);

        assertFalse(vendingMachine.validate("user", "the"));

    }

    @Test
    public void vendingMachineStartListItems() {

        System.setIn(new ByteArrayInputStream("1\n".getBytes()));
        try {
            VendingMachine.main(new String[]{});
        } catch (Exception e) {

        }
        assertEquals("Welcome to the Snack & Co. Vending Machine. Hope you enjoy the experience!\n" +
                "\n" +
                "Please enter the number corresponding to the option you want.\n" +
                "\n" +
                "1. List items\n" +
                "2. Buy Item\n" +
                "3. Staff Login\n" +
                "4. Exit\n" +
                "5. Staff Options\nItems available\n" +
                "\n" +
                "Snack -- Price -- Stock\n" +
                "1: SoftDrink | Price: 10.0 | Stock: 10\n" +
                "2: Juice | Price: 10.0 | Stock: 10\n" +
                "3: Water | Price: 10.0 | Stock: 10\n" +
                "4: MChocolate | Price: 10.0 | Stock: 10\n" +
                "5: Bounty | Price: 10.0 | Stock: 10\n" +
                "6: Mars | Price: 10.0 | Stock: 10\n" +
                "7: Sneakers | Price: 10.0 | Stock: 10\n" +
                "8: Original | Price: 10.0 | Stock: 10\n" +
                "9: Chicken | Price: 10.0 | Stock: 10\n" +
                "10: BBQ | Price: 14.0 | Stock: 10\n" +
                "11: SweetChilli | Price: 10.0 | Stock: 10\n" +
                "12: SourWorms | Price: 2.0 | Stock: 10\n" +
                "13: JellyBeans | Price: 2.5 | Stock: 10\n" +
                "14: LittleBears | Price: 1.6 | Stock: 10\n" +
                "15: PartMix | Price: 1.5 | Stock: 10\n" +
                "\n" +
                "Please enter the number corresponding to the option you want.\n" +
                "\n" +
                "1. List items\n" +
                "2. Buy Item\n" +
                "3. Staff Login\n" +
                "4. Exit\n" +
                "5. Staff Options\n" , baos.toString());


    }

    @Test
    public void vendingMachineStartBuyItem() {

        System.setIn(new ByteArrayInputStream("2\n".getBytes()));
        try {
            VendingMachine.main(new String[]{});
        } catch (Exception e) {

        }
        assertEquals("Welcome to the Snack & Co. Vending Machine. Hope you enjoy the experience!\n" +
                "\n" +
                "Please enter the number corresponding to the option you want.\n" +
                "\n" +
                "1. List items\n" +
                "2. Buy Item\n" +
                "3. Staff Login\n" +
                "4. Exit\n" +
                "5. Staff Options\nTRANSACTION INITIALISED\n" +
                "\n" +
                "\n" +
                "***Which action would you like to take?***\n" +
                "1. Add Item\n" +
                "2. Finalise Transaction\n" +
                "3. Discard Transaction and Exit\n\n", baos.toString());


    }


    @Test
    public void vendingMachineStartExit() {

        System.setIn(new ByteArrayInputStream("4\n".getBytes()));
        try {
            VendingMachine.main(new String[]{});
        } catch (Exception e) {

        }
        assertEquals("Welcome to the Snack & Co. Vending Machine. Hope you enjoy the experience!\n" +
                "\n" +
                "Please enter the number corresponding to the option you want.\n" +
                "\n" +
                "1. List items\n" +
                "2. Buy Item\n" +
                "3. Staff Login\n" +
                "4. Exit\n" +
                "5. Staff Options\n***PROGRAM EXITING, THANK YOU COME AGAIN\n", baos.toString());


    }

    @Test
    public void vendingMachineStartStaffOption() {

        System.setIn(new ByteArrayInputStream("5\n".getBytes()));
        try {
            VendingMachine.main(new String[]{});
        } catch (Exception e) {

        }
        assertEquals("Welcome to the Snack & Co. Vending Machine. Hope you enjoy the experience!\n" +
                "\n" +
                "Please enter the number corresponding to the option you want.\n" +
                "\n" +
                "1. List items\n" +
                "2. Buy Item\n" +
                "3. Staff Login\n" +
                "4. Exit\n" +
                "5. Staff Options\n" +
                "You are not logged in as a staff user.\n" +
                "Please enter the number corresponding to the option you want.\n" +
                "\n" +
                "1. List items\n" +
                "2. Buy Item\n" +
                "3. Staff Login\n" +
                "4. Exit\n" +
                "5. Staff Options\n", baos.toString());


    }


}
