package daoModel;

/** This class manipulates countries. */
public class Country {
    private int countryId;
    private String countryName;

    /** This method is a constructor for countries.
     @param countryId The country ID to be constructed.
     @param countryName The country name to be constructed.
     */
    public Country(int countryId, String countryName) {
        this.countryId = countryId;
        this.countryName = countryName;
    }

    /**  This method gets the country ID.
     @return Returns the country ID.
     */
    public int getCountryId() {
        return countryId;
    }

    /**  This method gets the country name.
     @return Returns the country name.
     */
    public String getCountryName() {
        return countryName;
    }

    /**  This method changes the observable list Country combo box choices in the add/update customer window to have both country ID and country name.
     @return Returns the String of country ID and country name in a string.
     */
    @Override
    public String toString(){
        return (countryId + " - " + countryName);
    }

}
