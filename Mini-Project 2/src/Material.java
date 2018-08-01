/***
 * this class defines a material that represents the construction of some
 * kind of currency
 */

public class Material
{
    // string of what this material is
    private String type;

    /***
     * constructor for a material
     * @param type material of a currency object
     */
    public Material(String type)
    {
        this.type = type;
    }

    /***
     * gets the material defined by this object
     * @return the string representation of this object
     */
    public String getType()
    {
        return type;
    }

    /***
     * sets name of this material
     * @param type string representation of this material
     */
    public void setType(String type)
    {
        this.type = type;
    }

    /***
     * {@inheritDoc}
     */
    @Override
    public String toString()
    {
        return type;
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
        if(!(o instanceof Material))
        {
            return false;
        }
        Material other = (Material) o;
        return other.getType().equals(type);
    }
}
