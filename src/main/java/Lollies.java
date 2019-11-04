public enum Lollies implements Snacks {
    SourWorms(2),
    JellyBeans(2.5),
    LittleBears(1.6),
    PartMix(1.5);

    private double price;

    Lollies(double price) {
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
