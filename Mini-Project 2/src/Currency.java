/***
 * This class defines a specific currency amount and stores additional
 * information about its physical aspects in the real world
 */

public class Currency implements Comparable<Currency>
{

    // value of this piece of currency
    private double value;

    // material this piece of currency is made of
    private Material material;

    // type of currency (i.e. dollar, euro, pound, etc...)
    private String denomination;

    /***
     * Constructor for a Currency object
     * @param value worth of this item
     * @param material real world material of this currency
     * @param denomination dollar, euro, pound, etc...
     */
    public Currency(double value, Material material, String denomination)
    {
        this.value = value;
        this.material = material;
        this.denomination = denomination;
    }

    /***
     * returns value of this object
     * @return amount this object is worth
     */
    public double getValue()
    {
        return value;
    }

    /***
     * sets value of this object
     * @param value what this object is worth
     */
    public void setValue(double value)
    {
        this.value = value;
    }

    /***
     * gets material this object is made of
     * @return real world material of this item
     */
    public Material getMaterial()
    {
        return material;
    }

    /***
     * sets material of this object
     * @param material material this is made of
     */
    public void setMaterial(Material material)
    {
        this.material = material;
    }

    /***
     * returns denomination of this currency
     * @return dollar, euro, pound, etc...
     */
    public String getDenomination()
    {
        return denomination;
    }

    /***
     * sets denomination of this currency
     * @param denomination dollar, euro, pound, etc...
     */
    public void setDenomination(String denomination)
    {
        this.denomination = denomination;
    }

    /***
     * {@inheritDoc}
     */
    @Override
    public String toString()
    {
        return "Value: " + value + " " + denomination + " Material: " + material.toString();
    }

    /***
     * {@inheritDoc}
     */
    @Override
    public int compareTo(Currency other)
    {
        return Double.compare(value, other.getValue());
    }

    /***
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object o)
    {
        if(o == this)
        {
            return true;
        }
        if(!(o instanceof Currency))
        {
            return false;
        }
        Currency other = (Currency) o;
        return other.value == value && other.material.equals(material) && other.denomination.equals(denomination);
    }
}
