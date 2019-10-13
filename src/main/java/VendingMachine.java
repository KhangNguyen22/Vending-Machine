import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;

public class VendingMachine {

    // options of payment
    private static double[] paymentOptions = new double[]{20, 10, 5, 2, 1, 0.5, 0.2, 0.1};

    // Map of snack items to quantity
    private HashMap<Snacks, Integer> snacks;

    // Max quantity final value
    private final int MAX_QUANTITY;

    // Staff login
    private boolean isAdmin = false;

    // List of transactions
    private ArrayList<Transaction> transactions;

    // ID for transactions
    private int id;

    // Cancelled transactions
    private ArrayList<Transaction> cancelledTransactions;

    // DB url
    private final static String DB_URL = "jdbc:mysql://localhost:3306/";

    // Can use a singleton later, load items from file
    private VendingMachine(int quantity) {
        transactions = new ArrayList<>();
        cancelledTransactions = new ArrayList<>();
        snacks = new HashMap<>();
        MAX_QUANTITY = quantity;
        snacks.put(Drinks.SoftDrink, 4);
        id = 1;


        Connection connect = null;
        // Loading to MySQL
        try {
            connect = DriverManager.getConnection("jdbc:mysql://db:3306/vending_machine","root","pass");
            Statement stmt = connect.createStatement();
            ResultSet resultSet = stmt.executeQuery("select * from whateever");

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connect != null) {
                try {
                    connect.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    /**
     * Fill all the items back to maximum quantity
     */
    private void fill() {
        for (Snacks snack : snacks.keySet()) {
            snacks.put(snack, MAX_QUANTITY);
        }
        System.out.println("Successful fill. The date and time is: " + new Date().toString());
    }

    /**
     * Buy the amount of snacks.
     *  @param snack The snack to buy
     * @param amount The amount of snacks
     * @param pay
     * @return amount of change
     */
    private double buy(Snacks snack, int amount, Double pay) {

        double change = pay - snack.getPrice() * amount;
        transactions.add(new VendingTransaction(id++, new Date(), amount, pay, change, snack.toString()));
        snacks.put(snack, snacks.get(snack) - 1);
        return change;
    }


    /**
     * Calculates the amount remaining required for the purchase
     * @param item The snack to be purchased
     * @param paid The amount the customer is paying
     * @param amount The number of snacks
     * @return The remaining amount
     */
    private double calculateRemaining(Snacks item, double paid, int amount) {
        return item.getPrice() * amount - paid;
    }


    /**
     * List utils can be changed into later
     * This is just a simple function that returns the list of items with their quantity and price
     * @return The String of items with quantity and price
     */
    private String listItems() {
        StringBuilder builder = new StringBuilder();
        int i = 1;
        for (Snacks snack : snacks.keySet()) {
            builder.append(i++ + ". ").
                    append(snack).
                    append(" | Price: ").
                    append(snack.getPrice());
        }

        builder.append("\n");
        return builder.toString();
    }

    // Hard coding these, can update later
    private String listOptions() {
        return "1. List items\n" + "2. Buy Item\n" + "3. Staff Login\n" + "4. Exit\n" + "5. Staff Options";
    }


    /**
     * Get the snack
     * @param snackItem The id or the name of the snack
     * @return The snack
     */
    private Snacks getSnack(String snackItem) {
        int id = 1;
        for (Snacks snack : snacks.keySet()) {
           if ((id + "").equals(snackItem) || snackItem.equalsIgnoreCase(snack.toString())) {
               return snack;
           }
           id++;
       }

        return null;
    }


    /**
     * Getter for isAdmin boolean
     * @return boolean of whether user is logged in
     */
    private boolean isAdmin() {
        return this.isAdmin;
    }


    /**
     * List out the vending machine options for the staff
     * @return The string containing the selections for staff
     */
    private String listVendingMachineStaff() {
        return "1. Fill Vending Machine\n" +
                "2. Print Daily Transactions\n" +
                "3. Print Available Stock\n" +
                "4. Print Cancelled Transactions\n";
    }

    /**
     * Validate whether a staffs credentials are correcct
     * @param name The username
     * @param password The password
     * @return boolean whether the login was successful
     */
    private boolean validate(String name, String password) {
        if (name.equals("admin") && password.equals("brawndo")) {
            isAdmin = true;
            return true;
        }
        return false;
    }


    /**
     * List the possible payment options available
     * @return The string of payment values.
     */
    private String listPayments() {
        return "1. $20\n" +
                "2. $10\n" +
                "3. $5\n" +
                "4. $2\n" +
                "5. $1\n" +
                "6. 50c\n" +
                "7. 20c\n" +
                "8. 10c\n";
    }


    /**
     * Add cancelled transactions when a user exits out of buying a snack
     * @param snack The snack that was cancelled
     * @param amount The the amount of the snack.
     */
    private void addCancelledTransaction(Snacks snack, int amount) {

        cancelledTransactions.add(new CancelledTransaction(id++, new Date(), amount, snack.getPrice() * amount, snack.toString()));
    }


    /**
     * Print the daily report of the transactions.
     */
    private void printDailyReport() {
        for (Transaction transaction : transactions) {
            System.out.println(transaction);
        }
    }

    /**
     * Print the cancelled transactions.
     */
    private void printCancelledTransactions() {
        for (Transaction transaction : cancelledTransactions) {
            System.out.println(transaction);
        }
    }

    /**
     * Print the available stock with the prices.
     */
    private void printAvailableStock() {
        StringBuilder builder = new StringBuilder();
        int i = 1;
        for (Snacks snack : snacks.keySet()) {
            builder.append(i++).
                    append(snack).
                    append("| Price: ").
                    append(snack.getPrice()).append("| Qty: ").append(snacks.get(snack)).append("\n");
        }

        builder.append("\n");
        System.out.println(builder.toString());
    }



    public static void main(String[] args) {

        VendingMachine vendingMachine = new VendingMachine(10);
        Scanner scan = new Scanner(System.in);


        System.out.println("Welcome to the Snack & Co. Vending Machine.\n");

        while (true) {
            System.out.println("Please enter the number corresponding to the option you want.\n");
            System.out.println(vendingMachine.listOptions());
            String line = scan.nextLine();
            int option;
            try {
                option = Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.println("Invalid, please enter a number.");
                continue;
            }

            // This is a switch case right now can update later to a simpler version
            switch (option) {

                // LIST ITEMS
                case 1:
                    System.out.println(vendingMachine.listItems());
                    break;


                // BUY PRODUCT
                case 2:
                    // Add product name
                    System.out.println("Please select the id of the snack you want to purchase.");
                    System.out.println(vendingMachine.listItems());
                    String i = scan.nextLine();

                    // Can add the ability to have more items than 1.
                    Snacks snack = vendingMachine.getSnack(i);
                    if (snack == null) {
                        System.out.println("Invalid item");
                        break;
                    }

                    // Fix this later on
                    if (vendingMachine.snacks.get(snack) <= 0) {
                        System.out.println("Out of stock");
                        break;
                    }

                    // Add as another sprint
                    System.out.println("Please select how many you want?");

                    // Catch error here later
                    int amount = Integer.parseInt(scan.nextLine());

                    if (amount <= 0) {
                        System.out.println("Invalid amount.");
                        break;
                    }
                    else if (amount > vendingMachine.snacks.get(snack)) {
                        System.out.println("Not enough stock.");
                        break;
                    }


                    System.out.println("Please enter the amount you are going to pay?");
                    double total = 0;

                    // Loop until successful
                    while (true) {
                        double remaining = vendingMachine.calculateRemaining(snack, total, amount);
                        if (remaining <= 0) {
                            System.out.println("Successful purchase. " + vendingMachine.buy(snack, amount, total) + " change returned.");
                            break;
                        }
                        System.out.println("$" + remaining + " is remaining");
                        System.out.println(vendingMachine.listPayments());

                        String paymentString = scan.nextLine();

                        // add way to cancel
                        if (paymentString.equalsIgnoreCase("cancel")) {
                            vendingMachine.addCancelledTransaction(snack, amount);
                            break;
                        }

                        // Error
                        int v = Integer.parseInt(paymentString);

                        if (v <= 0 || v > 8 ) {
                            System.out.println("Invalid option.");
                            continue;
                        }

                        total += paymentOptions[v - 1];
                    }

                    break;

                // STAFF LOGIN
                case 3:
                    if (vendingMachine.isAdmin()) {
                        break;
                    }

                    System.out.println("Please enter your username.");
                    String name = scan.nextLine();
                    System.out.println("Please enter your password.");
                    String password = scan.nextLine();

                    if (vendingMachine.validate(name, password)) {
                        System.out.println("Successfully Logged in.");
                    } else {
                        System.out.println("Unsuccessful login.");
                    }

                    break;

                // STAFF OPTIONS
                case 4:
                    if (vendingMachine.isAdmin()) {
                        System.out.println(vendingMachine.listVendingMachineStaff());
                        String v = scan.nextLine();
                        if (v.equals("1") || v.equalsIgnoreCase("fill")) {
                            vendingMachine.fill();
                        } else if (v.equals("2")) {
                            vendingMachine.printDailyReport();

                        } else if (v.equals("3")) {
                            vendingMachine.printAvailableStock();

                        } else if (v.equals("4")) {
                            vendingMachine.printCancelledTransactions();

                        } else {
                            System.out.println("Invalid staff command.");
                        }

                    } else {
                        System.out.println("Unauthorised, must be a staff.");
                    }

                    break;
                case 5:
                    return;
            }


        }
    }




}
