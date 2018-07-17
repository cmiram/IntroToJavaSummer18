/***
 * This class represents a name. A name contains a first and last name.
 */

public class Name
{

    private String firstName;

    private String lastName;

    public Name(String firstName, String lastName)
    {
        if(firstName.isEmpty() || lastName.isEmpty())
        {
            throw new IllegalArgumentException("Names cannot be empty");
        }

        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    @Override
    public String toString()
    {
        return String.format("%s %s", firstName, lastName);
    }
}
