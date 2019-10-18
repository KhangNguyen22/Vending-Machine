import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;

public class VendingMachine {

    // boolean for whether the user is an admin
    private boolean isAdmin;

    // Default max amount
    public VendingMachine(int amount) {
        isAdmin = false;
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

        if (args.length != 0) {
            return;
        }

        VendingMachine vendingMachine = new VendingMachine(10);
        Scanner scan = new Scanner(System.in);

        while(scan.hasNext()) {

            System.out.println("Please enter the number corresponding to the option you want.\n");
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
                    System.out.println("Items in vending machine");
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
