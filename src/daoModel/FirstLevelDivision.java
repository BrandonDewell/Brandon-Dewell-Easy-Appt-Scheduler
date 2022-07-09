package daoModel;

/** This class manipulates first level divisions. */
public class FirstLevelDivision {

    private int divisionId;
    private String division;
    private int countryId;

    /** This method is a constructor for first level divisions.
     @param divisionId The division ID to be constructed.
     @param division The division to be constructed.
     @param countryId The country ID to be constructed.
     */
    public FirstLevelDivision(int divisionId, String division, int countryId) {
        this.divisionId = divisionId;
        this.division = division;
        this.countryId = countryId;
    }

    /**  This method gets the division ID.
     @return Returns the division ID.
     */
    public int getDivisionId() {
        return divisionId;
    }

    /**  This method gets the division.
     @return Returns the division.
     */
    public String getDivision() {
        return division;
    }

    /**  This method gets the country ID.
     @return Returns the country ID.
     */
    public int getCountryId() {
        return countryId;
    }

    /**  This method changes the observable list State/Province combo box choices in the add/update customer window to have both division ID and division.
     @return Returns the String of division ID and division in a string.
     */
    @Override
    public String toString(){
        return (divisionId + " - " + division);
    }
}
