/***
 * This class throws and catches an illegal argument exception
 * while displaying a message and prints out its stack trace.
 */

public class IllegalArgumentExceptionCatch
{
    public static void main(String[] args)
    {
        // throws and catches an illegal argument exception by
        // attemption to get the integer value from a string
        try
        {
            Integer.valueOf("oops");
        } catch(IllegalArgumentException e)
        {
            System.out.println("Caught an IllegalArgumentException");
            System.out.println("StackTrace: ");
            e.printStackTrace();
        }
    }
}
