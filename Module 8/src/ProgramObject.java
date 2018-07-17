/***
 * This is an abstract class that implements interfaces drawable, rotatable,
 * resizable, and sounds. It also defines a field Name of type String
 */

public abstract class ProgramObject implements Drawable, Rotatable,
        Resizable, Sounds
{
    private String name;

    public ProgramObject(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }
}
