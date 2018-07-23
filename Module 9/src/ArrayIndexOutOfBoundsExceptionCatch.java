/***
 * This class throws and catches an array index out of bounds exception
 * while displaying a message and prints out its stack trace.
 */

public class ArrayIndexOutOfBoundsExceptionCatch
{
    public static void main(String[] args)
    {
        //throw and catch an array out of bounds exception by attempting
        // to access the 2nd array index of a single item array
        try
        {
            int[] oneItemArray = new int[]{1};
            int never = oneItemArray[2];
        } catch(ArrayIndexOutOfBoundsException e)
        {
            System.out.println("Caught an ArrayIndexOutOfBoundsException");
            System.out.println("StackTrace: ");
            e.printStackTrace();
        }
    }
}
