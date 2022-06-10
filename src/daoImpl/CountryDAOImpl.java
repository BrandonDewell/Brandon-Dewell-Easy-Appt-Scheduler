package daoImpl;

import daoInt.ICountryDAO;
import daoModel.Country;
import helper.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CountryDAOImpl implements ICountryDAO {

    @Override
    public ObservableList<Country> getAllCountriesOL() {

        ObservableList<Country> countriesOL = FXCollections.observableArrayList();

        try {
            String sql = "SELECT * FROM COUNTRIES";
            PreparedStatement ps = JDBC.connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int countryID = rs.getInt("Country_ID");
                String countryName = rs.getString("Country");  // I only got customer id and customer name and excluded lots
                // of other columns in the while loop walking through all the customer data.
                Country c = new Country(countryID, countryName);
                countriesOL.add(c);
            }
           // System.out.println(countriesOL.get(1));
        }
        catch (SQLException ex){
            ex.printStackTrace();
        }

        return countriesOL;
    }

}
