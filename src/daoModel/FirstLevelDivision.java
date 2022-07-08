package daoModel;

/** This class manipulates first level divisions. */
public class FirstLevelDivision {

    private int divisionId;
    private String division;
    private int countryId;

    /** This method is a constructor for first level divisions.
     @param divisionId The division Id to be constructed.
     @param division The division to be constructed.
     @param countryId The country Id to be constructed.
     */
    public FirstLevelDivision(int divisionId, String division, int countryId) {
        this.divisionId = divisionId;
        this.division = division;
        this.countryId = countryId;
    }

    /**
     @ return the division ID
     */
    public int getDivisionId() {
        return divisionId;
    }

    /**
     @ return the division
     */
    public String getDivision() {
        return division;
    }

    /**
     @ return the country ID
     */
    public int getCountryId() {
        return countryId;
    }

    /**
     @ return the String of division ID and division
     */
    @Override
    public String toString(){
        return (divisionId + " - " + division);
    }
}
