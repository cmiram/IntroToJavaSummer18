public class Product {

    private String name;
    
    private Packaging packaging;
    
    private double price;
    
    public Product(String name, Packaging packaging, double price) {
        this.name = name;
        this.packaging = packaging;
        this.price = price;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public Packaging getPackaging() {
        return packaging;
    }
    
    public void setPackaging(Packaging packaging) {
        this.packaging = packaging;
    }
    
    public double getPrice() {
        return price;
    }
    
    public void setPrice(double price) {
        this.price = price;
    }
    
    @Override
    public String toString() {
        return "Product: " + name + " Packaging: " + packaging.toString() + " Price: " + price;
    }
}
