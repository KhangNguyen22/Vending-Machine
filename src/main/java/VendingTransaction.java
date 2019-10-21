import java.util.*;

public class VendingTransaction implements Transaction {

    // mapping SnackID, to the Snack and quantity in the transaction
    private Map<Integer, SnackAndQuantity> itemsInTransaction = new HashMap<>();
    private int id;
    private Date date;
    private double changeGiven;
    private TransactionStatus status = TransactionStatus.PENDING;
    private double transactionTotal = 0.0;

    void setStatus(TransactionStatus statusToSet) {
        this.status = statusToSet;
    }

    public VendingTransaction(int id) {
        this.id = id;
    }

    public String getTransactionSummary() {
        StringBuilder builder = new StringBuilder();
        for(SnackAndQuantity s: itemsInTransaction.values()) {
            String item = "Snack: " + s.getSnack().toString() + " | Quantity: " + s.quantity + " | Subtotal: " + s.quantity * s.getSnack().getPrice() + "\n";
            builder.append(item);
        }
        return builder.toString();
    }

    public double getTotalPrice() {
        return this.transactionTotal;
    }

    public void commit(double changeGiven) {
        this.date = new Date();

        this.status = TransactionStatus.FINALISED;
    }

    public void cancelTransaction() {
        this.date = new Date();
        this.status = TransactionStatus.CANCELLED;
    }

    public Map<Integer, SnackAndQuantity> getTransactionItems() {
        return this.itemsInTransaction;
    }

    public List<SnackAndQuantity> getTransactionItemsAsList() {
        List<SnackAndQuantity> res = new ArrayList<>();
        for(SnackAndQuantity s: itemsInTransaction.values()) {
            res.add(s);
        }
        return res;
    }


    public void addProductToTransaction(int productId, Snacks snack, int quantity) {
        double costOfProducts = snack.getPrice() * quantity;
        SnackAndQuantity s = itemsInTransaction.get(productId);
        if(s == null) {
            itemsInTransaction.put(productId, new SnackAndQuantity(snack, quantity));
        } else {
            s.quantity = s.quantity + quantity;
        }
        this.transactionTotal = this.transactionTotal + costOfProducts;
    }

    @Override
    public void removeProductFromTransaction(Snacks snack, int quantity) {

    }
}