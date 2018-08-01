/***
 * this class defines a type of packaging for a product
 */

public class Packaging
{

    // type of packaging used
    private String type;

    /***
     * constructor for a type of packaging
     * @param type string descriptor of this packaging
     */
    public Packaging(String type)
    {
        this.type = type;
    }

    /***
     * returns the name of this packaging type
     * @return string descriptor of packaging type
     */
    public String getType()
    {
        return type;
    }

    /***
     * sets the type of packaging
     * @param type string descriptor of packaging type
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
}
