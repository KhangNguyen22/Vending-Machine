public enum Chips implements Snacks {
    Original(10),
    Chicken(10),
    BBQ(14),
    SweetChilli(10);

    private double price;

    Chips(double price) {
        this.price = price;
    }

    public double getPrice() {
        return this.price;
    }

    @Override
    public double buy(int amount, double paid) {
        return 0;
    }

    @Override
    public void setPrice(Double price) {
        this.price = price;
    }


}