/***
 * This class throws and catches a null pointer exception
 * while displaying a message and prints out its stack trace.
 */

public class NullPointerExceptionCatch
{
    public static void main(String[] args)
    {
        // throws and catches a null pointer exception by trying to add
        // to an Integer that was set to null
        try
        {
            Integer nullInt = null;
            System.out.println(nullInt + 1);
        } catch(NullPointerException e)
        {
            System.out.println("Caught a NullPointerException");
            System.out.println("StackTrace: ");
            e.printStackTrace();
        }
    }
}
