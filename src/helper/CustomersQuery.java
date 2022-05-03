package helper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public abstract class CustomersQuery {

    public static int insert(String CustomerName, String Address, String PostalCode, String Phone, Timestamp CreateDate, String CreatedBy, Timestamp LastUpdate, String LastUpdatedBy, int DivisionID) throws SQLException {
        //String sql = "INSERT INTO CUSTOMERS (Customer_Name, Address, Postal_Code, Phone, Create_Date, Created_By, Last_Update, Last_Updated_By, Division_ID) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";  // 3/15 12:13pm Question marks are bind variables starting at index 1.
        String sql = "INSERT INTO CUSTOMERS VALUES (NULL, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

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

    public static int update(int CustomerID, String CustomerName) throws SQLException {
        String sql = "UPDATE CUSTOMERS SET Customer_Name = ? WHERE Customer_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1, CustomerName);
        ps.setInt(2, CustomerID);
        int rowsAffected = ps.executeUpdate();
        return rowsAffected;
    }

    public static int delete(int CustomerID) throws SQLException {
        String sql = "DELETE FROM CUSTOMERS WHERE Customer_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setInt(1, CustomerID);
        int rowsAffected = ps.executeUpdate();
        return rowsAffected;
    }

    public static void select() throws SQLException {
        String sql = "SELECT * FROM CUSTOMERS";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            int customerID = rs.getInt("Customer_ID");
            String customerName = rs.getString("Customer_Name");
            System.out.print(customerID + " | ");
            System.out.print(customerName + "\n");
        }
    }

    // Overloaded select statement
    public static void select(String customerName) throws SQLException {
        String sql = "SELECT * FROM CUSTOMERS WHERE Customer_Name = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1, customerName);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            int customerID = rs.getInt("Customer_ID");
            String customerNameOL = rs.getString("Customer_Name");
            System.out.print(customerID + " | ");
            System.out.print(customerNameOL + "\n");
        }
    }

    // A different overloaded select statement
    public static void select(int divisionID) throws SQLException {
        String sql = "SELECT * FROM CUSTOMERS WHERE Division_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setInt(1, divisionID);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            int customerID = rs.getInt("Customer_ID");
            int divisionIDOL = rs.getInt("Division_ID");
            String customerName = rs.getString("Customer_Name");
            System.out.print(customerID + " | ");
            System.out.print(customerName + " | ");
            System.out.print(divisionIDOL + "\n");
        }
    }

}
