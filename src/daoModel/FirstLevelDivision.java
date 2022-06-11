package daoModel;

public class FirstLevelDivision {

    private int divisionId;
    private String division;
    private int countryId;

    public FirstLevelDivision(int divisionId, String division, int countryId) {
        this.divisionId = divisionId;
        this.division = division;
        this.countryId = countryId;
    }

    public int getDivisionId() {
        return divisionId;
    }

    public String getDivision() {
        return division;
    }

    public int getCountryId() {
        return countryId;
    }

    @Override
    public String toString(){
        return (divisionId + " - " + division);
    }
}
