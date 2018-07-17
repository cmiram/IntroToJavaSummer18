/***
 * This class is an extension of a ProgramObject that adds a field
 * age of type int
 */

public class Vehicle extends ProgramObject
{

    private int age;

    public Vehicle(String name, int age)
    {
        super(name);
        this.age = age;
    }

    @Override
    public void drawObject()
    {
        System.out.println("Drawing a Vehicle");
    }

    @Override
    public void resizeObject()
    {
        System.out.println("Resizing a Vehicle");
    }

    @Override
    public void rotateObject()
    {
        System.out.println("Rotating a Vehicle");
    }

    @Override
    public void playSound()
    {
        System.out.println("Vehicle sound");
    }

    public int getAge()
    {
        return age;
    }

    public void setAge(int age)
    {
        this.age = age;
    }
}
