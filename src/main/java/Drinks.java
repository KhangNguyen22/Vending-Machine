public enum Drinks implements Snacks {
    Water(10),
    SoftDrink(10),
    Juice(10);

    private double price;
    Drinks(double price)
    {

        this.price = price;
    }

    public double getPrice() {
        return this.price;
    }

    @Override
    public void setPrice(Double price) {
        this.price = price;
    }


}