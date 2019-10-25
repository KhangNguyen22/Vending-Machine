import java.util.*;

public class VendingMachine {
    Scanner scanner = new Scanner(System.in);

    // options of payment
    private static double[] paymentOptions = new double[]{20, 10, 5, 2, 1, 0.5, 0.2, 0.1};

    // ID to be used to create the next transaction
    // ensures unique transaction IDs
    private int currentTransactionID = 0;

    // vending machine inventory
    // stores snack id, snack name, and snack quantity
    Map<Integer, SnackAndQuantity> inventory = new HashMap<>();

    // Lists of transactions
    private List<Transaction> transactions = new ArrayList<>();

    // boolean for whether the user is an admin
    private boolean isAdmin;

    // Default max amount
    public VendingMachine(int maxItemCapacity) {
//        snacks = new HashMap<>();
        isAdmin = false;
        initialiseProducts();
    }

    private void initialiseProducts() {
        inventory.put(1, new SnackAndQuantity(Drinks.SoftDrink, 10));
        inventory.put(2, new SnackAndQuantity(Drinks.Juice, 10));
        inventory.put(3, new SnackAndQuantity(Drinks.Water, 10));
    }

    /*
     * List out the current items in stock with quantity and prices
     * */
    String listItems() {
        StringBuilder builder = new StringBuilder();
        for(Map.Entry<Integer, SnackAndQuantity> entry: inventory.entrySet()) {
            SnackAndQuantity snackAndQuantity = entry.getValue();
            Snacks snack = snackAndQuantity.getSnack();
            builder.append(entry.getKey() + ": " + snack + " | Price: " + snack.getPrice() + " | Stock: " + snackAndQuantity.quantity + "\n");
        }
        return builder.toString();
    }

    // Returns options of commands available
    String listOptions(){
        return "1. List items\n" + "2. Buy Item\n" + "3. Staff Login\n" + "4. Exit\n" + "5. Staff Options";
    }

    String listTransactionOptions() {
        return "1. Add Item\n" + "2. Remove Item\n" + "3. Finalise Transaction\n" + "4. Discard Transaction and Exit\n";
    }

    /**
     * Getter for isAdmin boolean
     * @return boolean of whether user is logged in
     */
    boolean isAdmin() {
        return this.isAdmin;
    }



    /**
     * Validate whether a staffs credentials are correcct
     * @param name The username
     * @param password The password
     * @return boolean whether the login was successful
     */
    boolean validate(String name, String password) {
        // Default login
        if (name.equals("beefsupreme") && password.equals("hunter2")) {
            isAdmin = true;
            return true;
        }
        return false;
    }

    /**
     * List the possible payment options available
     * @return The string of payment values.
     */
    String listPayments() {
        return "1. $20\n" +
                "2. $10\n" +
                "3. $5\n" +
                "4. $2\n" +
                "5. $1\n" +
                "6. 50c\n" +
                "7. 20c\n" +
                "8. 10c\n" +
		"99. QUIT";
    }

    void addProductToTransaction(Transaction transaction) {
        System.out.println("Please select the id of the snack you want to purchase.");
        System.out.println(this.listItems());
        String i = scanner.nextLine();
        Integer id = null;
        try{
            id = Integer.parseInt(i);
        } catch(NumberFormatException e) {
            // iterate through and get the snack matching the string provided
            String userIn = i.toLowerCase();
            for(Map.Entry<Integer, SnackAndQuantity> entry: inventory.entrySet()) {
                SnackAndQuantity s = entry.getValue();
                String currSnack = s.getSnack().toString().toLowerCase();

                if(userIn.equals(currSnack)) {
                    id = entry.getKey();
                }
            }
        }

        SnackAndQuantity selectedSnack = this.inventory.get(id);

        if (selectedSnack == null) {
            System.out.println("Invalid item");
            return;
        }

        if (selectedSnack.quantity <= 0) {
            System.out.println("Out of stock");
            return;
        }

        System.out.println("How many would you like to purchase?");
        String input = scanner.nextLine();
        int amount = Integer.parseInt(input);
        if (amount <= 0) {
            System.out.println("Invalid amount.");
            return;
        }

        if(amount > selectedSnack.quantity) {
            System.out.println("Not enough stock.");
            return;
        }

        System.out.println("Adding to transaction");
        transaction.addProductToTransaction(id, selectedSnack.getSnack(), amount);
        selectedSnack.decrementQuantityBy(amount);


    }

    void finaliseTransaction(Transaction transaction) {
        System.out.println("***FINALISING TRANSACTION***\n");
        double transactionTotal = transaction.getTotalPrice();
        System.out.println("***Transaction Summary: \n\n");
        System.out.println(transaction.getTransactionSummary());
        System.out.println("The total for this transaction is: " + transactionTotal);
        double amountPaidSoFar = 0.0;

        while(amountPaidSoFar < transactionTotal) {
            System.out.println("Amount remaining to be paid: $" + (transactionTotal - amountPaidSoFar));
            System.out.println("Please enter the amount you are going to pay: ");
            System.out.println(this.listPayments());
            String paymentString = scanner.nextLine();

            int v = Integer.parseInt(paymentString);

	    if (v == 99){
		    System.out.println("Quitting transaction.");
		    System.out.println("Refunding amount of $" + amountPaidSoFar);
		    return;
	    }
	    
            else if (v <= 0 || v > 8 ) {
		System.out.println("");
                System.out.println("Invalid option.");
                continue;
            }


            amountPaidSoFar += paymentOptions[v - 1];
        }

        double change = amountPaidSoFar - transactionTotal;
        System.out.println("Change due: " + change);

        transaction.commit(change);
        System.out.println("Transaction has been finalised.");

    }

    Transaction initialiseNewTransaction() {
        currentTransactionID = currentTransactionID + 1;
        return new VendingTransaction(currentTransactionID);
    }

    void handleTransaction() {
        // Add product name
        Transaction tran = initialiseNewTransaction();
        this.transactions.add(tran);
        System.out.println("TRANSACTION INITIALISED");
        boolean transactionShouldExit = false;

        while(!transactionShouldExit) {
            System.out.println("\n\n***Which action would you like to take?***");
            System.out.println(listTransactionOptions());
            String selection = scanner.nextLine();
            int intSelection = Integer.parseInt(selection);
            switch(intSelection) {
                case 1:
                    addProductToTransaction(tran);
                    break;
                case 2:
                    System.out.println("Remove item functionality not working at this time");
                    break;
                case 3:
                    finaliseTransaction(tran);
                    transactionShouldExit = true;
                    break;
		case 4:
		    System.out.println("Exiting transaction handler. Returning to main menu.");
		    transactionShouldExit = true;
		    break;
            }
        }

    }

    void handleLogin() {
        if (this.isAdmin()) {
            System.out.println("Already logged in");
            return;
        }
        System.out.println("Please enter your username.");
        String name = scanner.nextLine();
        System.out.println("Please enter your password.");
        String password = scanner.nextLine();

        if (this.validate(name, password)) {
            System.out.println("Successfully Logged in.");
        } else {
            System.out.println("Unsuccessful login.");
        }
    }

    public void handleStaffOptions() {

        String option = scanner.nextLine();
        if (option.equalsIgnoreCase("1") || option.equalsIgnoreCase("fill")) {
            fill();
        } else if (option.equals("2")) {
            printDailyTransactions();
        } else if (option.equals("4")) {

        } else {
            System.out.println("Invalid option.");
        }
    }


    /**
     * Print the daily transactions
     */
    public void printDailyTransactions() {
        for (Transaction transaction : transactions) {
            if (transaction.getStatus() == TransactionStatus.FINALISED) {
                System.out.println(transaction.getTransactionSummary());
            }
        }
    }

    /**
     * Fill all he items in the inventory
     * Loops through the values of the hashmap and sets each to 10.
     */
    public void fill() {
        for (SnackAndQuantity snack : inventory.values()) {
            snack.quantity = 10;
        }
        System.out.println("Vending machine has been filled.");
    }

    public String listStaffOptions() {
        return "1. Fill\n" +
                "2. View Daily Transactions\n" +
                "3. View Cancelled Transactions\n" +
                "4. Change Product Prices\n";
    }

    /*
     * As of now options will be
     * 1. List Items
     * 2. Buy Items
     * 3. Staff login
     * 4. Staff options
     * 5. Exit
     *
     */
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        boolean programShouldExit = false;
        if (args.length != 0) {
            return;
        }

        System.out.println("Welcome to the Snack & Co. Vending Machine. Hope you enjoy the experience!\n");
        VendingMachine vendingMachine = new VendingMachine(10);

        while(!programShouldExit) {
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

            switch (option) {
                // List Items
                case 1:
                    System.out.println("Items available");
                    System.out.println(vendingMachine.listItems());
                    break;
                // Create transaction
                case 2:
                    vendingMachine.handleTransaction();
                    break;
                // Log In
                case 3:
                    vendingMachine.handleLogin();
                    break;
                case 4:
                    programShouldExit = true;
                    break;
                case 5:
                    System.out.println(vendingMachine.listStaffOptions());
                    vendingMachine.handleStaffOptions();

            }
        }
    }

}

