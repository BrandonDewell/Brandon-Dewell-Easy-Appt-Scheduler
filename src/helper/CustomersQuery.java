package helper;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

public abstract class CustomersQuery {

    public static int insert(String CustomerName, String Address, String PostalCode, String Phone, Timestamp CreateDate, String CreatedBy, Timestamp LastUpdate, String LastUpdatedBy, int DivisionID) throws SQLException {
        String sql = "INSERT INTO CUSTOMERS (Customer_Name, Address, Postal_Code, Phone, Create_Date, Created_By, Last_Update, Last_Updated_By, Division_ID) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";  // 3/15 12:13pm Question marks are bind variables starting at index 1.
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1, CustomerName);
        ps.setString(2, Address);
        ps.setString(3, PostalCode);
        ps.setString(4, Phone);
        ps.setTimestamp(5, CreateDate);
        ps.setString(6, CreatedBy);
        ps.setTimestamp(7, LastUpdate);
        ps.setString(8, LastUpdatedBy);
        ps.setInt(9, DivisionID);
        int rowsAffected = ps.executeUpdate();
        return rowsAffected;
    }
}
