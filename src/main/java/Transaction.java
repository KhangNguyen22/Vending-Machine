import java.util.List;
import java.util.Map;

public interface Transaction {
    void addProductToTransaction(int productId, Snacks snack, int quantity);

    void removeProductFromTransaction(Snacks snack, int quantity);

    /**
     * Method which commits the transaction to storage
     */
    void commit(double changeGivenToCustomer);

    void cancelTransaction();

    double getTotalPrice();

    String getTransactionSummary();

    List<SnackAndQuantity> getTransactionItemsAsList();

    public Map<Integer, SnackAndQuantity> getTransactionItems();

}
