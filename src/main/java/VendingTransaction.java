import java.util.Date;

public class VendingTransaction implements Transaction {

    private int id;
    private Date date;
    private int amount;
    private double pay;
    private double change;
    private String item;

    public VendingTransaction(int id, Date date, int amount, double pay, double change, String item) {
        this.id = id;
        this.date = date;
        this.amount = amount;
        this.pay = pay;
        this.change = change;
        this.item = item;
    }

    @Override
    public void getTransaction(int id) {

    }

    @Override
    public String toString() {
        return String.format("Transaction - %d\nDate - %s\nAmount - %d\nItem - %s\nPaid - %.2f\nChange - %.2f", id, date.toString(), amount, item, pay, change);
    }
}
