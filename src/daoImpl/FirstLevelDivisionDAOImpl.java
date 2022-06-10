package daoImpl;

import daoInt.IFirstLevelDivisionDAO;
import daoModel.FirstLevelDivision;
import helper.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FirstLevelDivisionDAOImpl implements IFirstLevelDivisionDAO {

    @Override
    public ObservableList<FirstLevelDivision> getAllFirstLevelDivisionsOL(int countryId) {

        ObservableList<FirstLevelDivision> firstLevelDivisionsOL = FXCollections.observableArrayList();

        try {
            String sql = "SELECT * FROM FIRST_LEVEL_DIVISIONS WHERE COUNTRY_ID = ?";
            PreparedStatement ps = JDBC.connection.prepareStatement(sql);
            ps.setInt(1, countryId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int divId = rs.getInt("Division_ID");
                String div = rs.getString("Division");  // I only got customer id and customer name and excluded lots
                // of other columns in the while loop walking through all the customer data.
                int countryIdX = rs.getInt("Country_ID");
                FirstLevelDivision fld = new FirstLevelDivision(divId, div, countryIdX);
                firstLevelDivisionsOL.add(fld);
            }
        }
        catch (SQLException ex){
            ex.printStackTrace();
        }

        return firstLevelDivisionsOL;
    }

}
