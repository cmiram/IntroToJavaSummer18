/***
 * This class throws a class cast exception and catches it
 * while displaying its message and writing out its stack trace.
 */

public class ClassCastExceptionThrown
{
    public static void main(String[] args)
    {
        // throw and catch the exception
        try
        {
            throw new ClassCastException("My Illegal Class Cast");
        } catch(ClassCastException e)
        {
            System.out.println("Caught a ClassCastException");
            System.out.println("Message: " + e.getMessage());
            System.out.println("StackTrace: ");
            e.printStackTrace();
        }
    }
}
