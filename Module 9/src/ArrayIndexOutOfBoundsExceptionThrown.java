/***
 * This class throws and catches an ArrayIndexOutOfBoundsException
 * while diplaying its message and writing out its stack trace.
 */

public class ArrayIndexOutOfBoundsExceptionThrown
{
    public static void main(String[] args)
    {
        //throw and catch the exception
        try
        {
            throw new ArrayIndexOutOfBoundsException("My Illegal Argument");
        } catch(ArrayIndexOutOfBoundsException e)
        {
            System.out.println("Caught an ArrayIndexOutOfBoundsException");
            System.out.println("Message: " + e.getMessage());
            System.out.println("StackTrace: ");
            e.printStackTrace();
        }
    }
}
