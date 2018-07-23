/***
 * This class throws and catches a null pointer exception
 * while displaying its message and prints out its stack trace.
 */

public class NullPointerExceptionThrown
{
    public static void main(String[] args)
    {
        // throw and catch the exception
        try
        {
            throw new NullPointerException("My Illegal Argument");
        } catch(NullPointerException e)
        {
            System.out.println("Caught a NullPointerException");
            System.out.println("Message: " + e.getMessage());
            System.out.println("StackTrace: ");
            e.printStackTrace();
        }
    }
}
