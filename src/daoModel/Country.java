package daoModel;

import helper.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class Country {
    private int countryId;
    private String countryName;

    public Country(int countryId, String countryName) {  // constructor
        this.countryId = countryId;
        this.countryName = countryName;
    }

    public int getCountryId() {
        return countryId;
    }  // I don't need setters for this Class since there are only 3 countries the company operates out of,
    // and that will not change.

    public String getCountryName() {
        return countryName;
    }

    @Override
    public String toString(){
        return (countryId + " - " + countryName);
    }


}
