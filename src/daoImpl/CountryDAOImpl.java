package daoImpl;

import daoInt.ICountryDAO;
import daoModel.Country;
import helper.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/** This method implements an Interface and provides a method to do a SQL call to the database. */
public class CountryDAOImpl implements ICountryDAO {

    /** This method gets an Observable List via a SQL call to the database.
     @return Returns the countriesOL observable list of Country objects.
     */
    @Override
    public ObservableList<Country> getAllCountriesOL() {

        ObservableList<Country> countriesOL = FXCollections.observableArrayList();

        try {
            String sql = "SELECT * FROM COUNTRIES";
            PreparedStatement ps = JDBC.connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int countryID = rs.getInt("Country_ID");
                String countryName = rs.getString("Country");
                Country c = new Country(countryID, countryName);
                countriesOL.add(c);
            }
        }
        catch (SQLException ex){
            ex.printStackTrace();
        }
        return countriesOL;
    }
}
