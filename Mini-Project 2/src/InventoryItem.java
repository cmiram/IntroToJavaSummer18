/***
 * this class defines an inventory item within the vending machine
 */

public class InventoryItem
{
    // the product within this inventory item
    private Product product;

    // amount of product available
    private int quantity;

    /***
     * constructor for an InventoryItem
     * @param product the actual product in this item
     * @param quantity amount available
     */
    public InventoryItem(Product product, int quantity)
    {
        this.product = product;
        this.quantity = quantity;
    }

    /***
     * returns the Product this represents
     * @return this item's product
     */
    public Product getProduct()
    {
        return product;
    }

    /***
     * sets the product defined in this item
     * @param product this item's product
     */
    public void setProduct(Product product)
    {
        this.product = product;
    }

    /***
     * returns amount available
     * @return amount available
     */
    public int getQuantity()
    {
        return quantity;
    }

    /***
     * set amount available of this item
     * @param quantity new amount available
     */
    public void setQuantity(int quantity)
    {
        this.quantity = quantity;
    }

    /***
     * {@inheritDoc}
     */
    @Override
    public String toString()
    {
        return product.toString() + " Quantity: " + quantity;
    }
}
