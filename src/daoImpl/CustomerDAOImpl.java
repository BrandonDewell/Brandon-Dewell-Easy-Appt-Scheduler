package daoImpl;

import daoInt.CustomerDAO;
import daoModel.Customer;
import helper.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class CustomerDAOImpl implements CustomerDAO {  // write sql things here
    @Override
    public void select() throws SQLException {
        String sql = "SELECT * FROM CUSTOMERS";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            int customerID = rs.getInt("Customer_ID");
            String customerName = rs.getString("Customer_Name");  // i only got customer id and customer name and excluded lots of other columns in the while loop walking through all the customer data.
            System.out.print(customerID + " | ");  // use print instead of println if I want to print customer id and customer name info for each customer on multiple lines.  Malcom's JDBC webinar at 1hr 7mins.
            System.out.print(customerName + "\n");
        }
    }

    @Override
    public int insert() {
        return 0;
    }

    @Override
    public int update() {
        return 0;
    }

    @Override
    public int delete() {
        return 0;
    }

    public static int insert(String CustomerName, String Address, String PostalCode, String Phone, Timestamp CreateDate, String CreatedBy, Timestamp LastUpdate, String LastUpdatedBy, int DivisionID) throws SQLException {
        //String sql = "INSERT INTO CUSTOMERS (Customer_Name, Address, Postal_Code, Phone, Create_Date, Created_By, Last_Update, Last_Updated_By, Division_ID) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";  // 3/15 12:13pm Question marks are bind variables starting at index 1.
        String sql = "INSERT INTO CUSTOMERS VALUES (NULL, ?, ?, ?, ?, ?, ?, ?, ?, ?)";  // have to put in NULL to let mysql handle inputting values for the Customer_ID.

        PreparedStatement ps = JDBC.connection.prepareStatement(sql);  // anytime there is a preparedStatement() called in a method, you must throw a SQLException in the header/signature of the method that calls the preparedStatement().
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

    public static int update(int CustomerID, String CustomerName) throws SQLException {  // this function updates the customer name
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

    public static ObservableList<Customer> getAllCustomers(){
        ObservableList<Customer> custList = FXCollections.observableArrayList();

        try{
            String sql = "SELECT * FROM Customer";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int custId = rs.getInt("Customer_ID");
                String custName = rs.getString("Customer_Name");
                String addr = rs.getString("Address");
                String pcode = rs.getString("Postal_Code");
                int ph = rs.getInt("Phone");
                int divId = rs.getInt("Division_ID");
                Customer c = new Customer(custId, custName, addr, pcode, ph, divId);
                custList.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return custList;
    }
}
