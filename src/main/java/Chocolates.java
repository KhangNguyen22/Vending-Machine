public enum Chocolates implements Snacks {
    MChocolate(10),
    Bounty(10),
    Mars(10),
    Sneakers(10);

    private double price;

    Chocolates(double price) {
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