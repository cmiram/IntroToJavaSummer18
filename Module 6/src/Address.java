/***
 * This class represents an address. An address is made up of a street,
 * city, state, and zip code.
 */

public class Address
{

    private String street;

    private String city;

    private String state;

    private String zipCode;

    public Address(String street, String city, String state, String zipCode)
    {
        // State abbreviations are always 2 characters
        if(state.length() != 2)
        {
            throw new IllegalArgumentException("Invalid state abbreviation");
        }

        // zip codes are always 5 digits long
        if(zipCode.length() != 5)
        {
            throw new IllegalArgumentException("Invalid 5 digit zip code");
        }

        this.street = street;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
    }

    public String getStreet()
    {
        return street;
    }

    public void setStreet(String street)
    {
        this.street = street;
    }

    public String getCity()
    {
        return city;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    public String getState()
    {
        return state;
    }

    public void setState(String state)
    {
        this.state = state;
    }

    public String getZipCode()
    {
        return zipCode;
    }

    public void setZipCode(String zipCode)
    {
        this.zipCode = zipCode;
    }

    @Override
    public String toString()
    {
        return String.format("%s\n%s, %s\n%s", street, city, state, zipCode);
    }
}
