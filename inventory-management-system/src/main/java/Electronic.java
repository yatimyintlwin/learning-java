public class Electronic extends Product{
    private int warrantyPeriod;

    public Electronic(String name, double price, int warrantyPeriod) {
        super(name, price);
        this.warrantyPeriod = warrantyPeriod;
    }

    public int getWarrantyPeriod() {
        return warrantyPeriod;
    }

    public void setWarrantyPeriod(int warrantyPeriod) {
        this.warrantyPeriod = warrantyPeriod;
    }

    @Override
    public String toString() {
        return "Electronic{" +
                "name = " + getName() +
                ", price = " + getPrice() +
                ", warrantyPeriod = " + warrantyPeriod +
                "}";
    }
}
