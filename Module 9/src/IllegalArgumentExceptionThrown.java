/***
 * This class throws an illegal argument exception and catches it
 * while displaying the message from the exception and its stack trace
 */

public class IllegalArgumentExceptionThrown
{
    public static void main(String[] args)
    {
        // throw and catch the exception
        try
        {
            throw new IllegalArgumentException("My Illegal Argument");
        } catch(IllegalArgumentException e)
        {
            System.out.println("Caught an IllegalArgumentException");
            System.out.println("Message: " + e.getMessage());
            System.out.println("StackTrace: ");
            e.printStackTrace();
        }
    }
}
