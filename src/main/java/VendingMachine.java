import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;

public class VendingMachine {

    // HashMap of snack items and respective quantity
    private HashMap<Snacks, Integer> snacks;

    // boolean for whether the user is an admin
    private boolean isAdmin;

    // Default max amount
    public VendingMachine(int amount) {
        snacks = new HashMap<>();
        snacks.put(Drinks.SoftDrink, amount);
        isAdmin = false;
    }

    /*
     * List out the current items in stock with quantity and prices
     * */

    private String listItems(){
        StringBuilder builder = new StringBuilder();
        int i = 1;
        for (Snacks snack : snacks.keySet()) {
            builder.append(i++ + ". ").
                    append(snack).
                    append(" | Price: ").

                    // Need to unHardCode This !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                            append(9999999);
        }
        builder.append("\n");
        return builder.toString();
    }

    // Returns options of commands available
    private String listOptions(){
        return "1. List items\n" + "2. Buy Item\n" + "3. Staff Login\n" + "4. Exit\n" + "5. Staff Options";
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
        boolean flag = true;
        if (args.length != 0) {
            return;
        }

        System.out.println("Welcome to the Snack & Co. Vending Machine.\n");

        VendingMachine vendingMachine = new VendingMachine(10);
        Scanner scan = new Scanner(System.in);

        while(flag) {

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

                // List Items
                case 1:
                    System.out.println("Items available");
                    System.out.println(vendingMachine.listItems());
                    break;

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
            }


        }
    }

    /**
     * Getter for isAdmin boolean
     * @return boolean of whether user is logged in
     */
    private boolean isAdmin() {
        return this.isAdmin;
    }


    /**
     * Validate whether a staffs credentials are correcct
     * @param name The username
     * @param password The password
     * @return boolean whether the login was successful
     */
    private boolean validate(String name, String password) {
        // Default login
        if (name.equals("beefsupreme") && password.equals("hunter2")) {
            isAdmin = true;
            return true;
        }
        return false;
    }

}