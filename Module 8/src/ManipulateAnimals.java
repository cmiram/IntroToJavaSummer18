/***
 * This class provides an entry point to test the interfaces and abstract
 * class by creating 4 new objects and calling defined methods which
 * will write to the console.
 */

public class ManipulateAnimals
{
    public static void main(String[] args)
    {
        // create array of 4 ProgramObjects
        ProgramObject[] objects = new ProgramObject[4];
        objects[0] = new Animal("Cow");
        objects[1] = new Animal("Chicken");
        objects[2] = new Vehicle("Car", 1);
        objects[3] = new Vehicle("Boat", 2);

        // loop over each object and call the 4 interface methods
        for(ProgramObject obj : objects)
        {
            // write out object name for ease of distinguishing where loop ends
            System.out.println("I am a " + obj.getName());
            obj.drawObject();
            obj.rotateObject();
            obj.resizeObject();
            obj.playSound();
            System.out.print("\n");
        }
    }
}
