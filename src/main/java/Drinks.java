public enum Drinks implements Snacks {
    Water(10),
    SoftDrink(10),
    Juice(10);

    private double price;
    Drinks(double price)
    {
        this.price = price;
    }

}
