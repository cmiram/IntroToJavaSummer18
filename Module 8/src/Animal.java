/***
 * This class is a concrete implementation of ProgramObject
 */

public class Animal extends ProgramObject
{
    public Animal(String name)
    {
        super(name);
    }

    @Override
    public void drawObject()
    {
        System.out.println("Drawing an Animal");
    }

    @Override
    public void resizeObject()
    {
        System.out.println("Resizing an Animal");
    }

    @Override
    public void rotateObject()
    {
        System.out.println("Rotating an Animal");
    }

    @Override
    public void playSound()
    {
        System.out.println("Animal sound");
    }
}
