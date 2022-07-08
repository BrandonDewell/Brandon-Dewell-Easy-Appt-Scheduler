package daoModel;

/** This class manipulates countries. */
public class Country {
    private int countryId;
    private String countryName;

    /** This method is a constructor for countries.
     @param countryId The country ID to be constructed.
     @param countryName The country name to be constructed.
     */
    public Country(int countryId, String countryName) {  // constructor
        this.countryId = countryId;
        this.countryName = countryName;
    }

    /**
     @ return the country ID
     */
    public int getCountryId() {
        return countryId;
    }

    /**
     @ return the country name
     */
    public String getCountryName() {
        return countryName;
    }

    /**
     @ return the String of country ID and country name
     */
    @Override
    public String toString(){
        return (countryId + " - " + countryName);
    }

}
