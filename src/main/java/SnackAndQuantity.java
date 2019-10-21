public class SnackAndQuantity {

    public SnackAndQuantity(Snacks snack, int initialQuantity) {
        this.snack = snack;
        this.quantity = initialQuantity;
    }
    private Snacks snack;
    public int quantity;

    public Snacks getSnack() {
        return this.snack;
    }

    public void incrementQuantityBy(int x) {
        this.quantity = this.quantity + x;
    }

    public void decrementQuantityBy(int x) {
        this.quantity = this.quantity - x;

    }

}