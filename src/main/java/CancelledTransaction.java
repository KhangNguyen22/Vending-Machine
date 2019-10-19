import java.util.Date;
import java.util.Map;
public class CancelledTransaction implements Transaction {


    private int id;
    private Date date;
    private int amount;
    private double total;
    private String item;
    public CancelledTransaction(int id, Date date, int amount, double total, String item) {
        this.id = id;
        this.date = date;
        this.amount = amount;
        this.total = total;
        this.item = item;
    }

//    @Override
//    public void getTransaction(int id) {
//
//    }

    @Override
    public String toString() {
        return String.format("Transaction - %d\nDate - %s\nAmount - %d\nTotal - %f\nItem - %s", id, date, amount, total, item);
    }
}