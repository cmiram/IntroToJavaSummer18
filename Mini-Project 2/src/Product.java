/***
 * this class describes a product sold in the vending machine
 */

public class Product
{
    // name of the product
    private String name;

    // type of packaging this item comes in
    private Packaging packaging;

    // cost of this product
    private double price;

    /***
     * constructor for a product sold in the vending machine
     * @param name string of the name
     * @param packaging packing object describing its container
     * @param price cost of this item
     */
    public Product(String name, Packaging packaging, double price)
    {
        this.name = name;
        this.packaging = packaging;
        this.price = price;
    }

    /***
     * gets the name of this product
     * @return the string name
     */
    public String getName()
    {
        return name;
    }

    /***
     * sets the name of this product
     * @param name string name of product
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /***
     * gets the packaging of this item
     * @return packing object of this product
     */
    public Packaging getPackaging()
    {
        return packaging;
    }

    /***
     * sets the packaging container of this product
     * @param packaging packaing object to set
     */
    public void setPackaging(Packaging packaging)
    {
        this.packaging = packaging;
    }

    /***
     * gets the cost of this product
     * @return price of this item
     */
    public double getPrice()
    {
        return price;
    }

    /***
     * sets the price of this product
     * @param price price of this item
     */
    public void setPrice(double price)
    {
        this.price = price;
    }

    /***
     * {@inheritDoc}
     */
    @Override
    public String toString()
    {
        return "Product: " + name + " Packaging: " + packaging.toString() + " Price: " + price;
    }
}
