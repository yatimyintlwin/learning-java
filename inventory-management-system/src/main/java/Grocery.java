public class Grocery extends Product{
    private boolean isOrganic;

    public Grocery(String name, double price, boolean isOrganic) {
        super(name, price);
        this.isOrganic = isOrganic;
    }

    public boolean isOrganic() {
        return isOrganic;
    }

    public void setOrganic(boolean organic) {
        isOrganic = organic;
    }

    @Override
    public String toString() {
        return "Grocery{" +
                "name = " + getName() +
                ", price = " + getPrice() +
                ", isOrganic = " + isOrganic +
                "}";
    }
}
