/***
 * this class defines a change object which holds a currency amount
 * it is associated to along with the quantity held
 */

public class Change implements Comparable<Change>
{
    // currency object associated to this Change object
    private Currency currency;

    // amount of this currency in vending machine
    private int quantity;

    /***
     * Constructor for Change object
     * @param currency Currency object to associate
     * @param quantity amount of currency object available
     */
    public Change(Currency currency, int quantity)
    {
        if(quantity < 0)
        {
            throw new IllegalArgumentException("Quantity must be 0 or larger");
        }

        this.currency = currency;
        this.quantity = quantity;
    }

    /***
     * gets currency object
     * @return currency object
     */
    public Currency getCurrency()
    {
        return currency;
    }

    /***
     * sets currency object
     * @param currency Currency to associate
     */
    public void setCurrency(Currency currency)
    {
        this.currency = currency;
    }

    /***
     * gets quantity available
     * @return amount available
     */
    public int getQuantity()
    {
        return quantity;
    }

    /***
     * sets quantity available
     * @param quantity amount available to set
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
        return currency.toString() + " Quantity: " + quantity;
    }

    /***
     * {@inheritDoc}
     */
    @Override
    public int compareTo(Change o)
    {
        return currency.compareTo(o.currency);
    }
}
