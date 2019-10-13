/**
 * Snack class
 *
 * Just Create snacks from DB given name and price.
 */
public class Snack {

    private String name;
    private Double price;

    public Snack(String name, Double price) {
        this.name = name;
        this.price = price;
    }

    public Double getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }
}
