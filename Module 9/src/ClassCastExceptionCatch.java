/***
 * This class throws and catches a class cast exception
 * while displaying a message and prints out its stack trace.
 */

public class ClassCastExceptionCatch
{
    public static void main(String[] args)
    {
        // throw and catches a class cast exception by attempting
        // to cast an empty object to an integer
        try
        {
            Integer never = (Integer) new Object();
        } catch(ClassCastException e)
        {
            System.out.println("Caught a ClassCastException");
            System.out.println("StackTrace: ");
            e.printStackTrace();
        }
    }
}
