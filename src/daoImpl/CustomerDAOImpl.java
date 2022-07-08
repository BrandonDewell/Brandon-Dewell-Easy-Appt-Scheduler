package daoImpl;

import daoInt.ICustomerDAO;
import daoModel.Customer;
import helper.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/** This method implements an Interface and provides methods to do  SQL calls to the database. */
public class CustomerDAOImpl implements ICustomerDAO {

    /** This method gets an Observable List via a SQL call to the database.
     @return Returns the allCustomersOL observable list of Customer objects.
     */
    public ObservableList<Customer> getAllCustomersOL() {

        ObservableList<Customer> allCustomersOL = FXCollections.observableArrayList();

        try {
            String sql = "SELECT Customer_ID, Customer_Name, Address, Postal_Code, Phone, Customers.Division_ID, Division, " +
                    "First_Level_Divisions.Country_ID, Country FROM Customers, First_Level_Divisions, Countries WHERE " +
                    "Customers.Division_ID = First_Level_Divisions.Division_ID AND First_Level_Divisions.Country_ID = Countries.Country_ID";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int custId = rs.getInt("Customer_ID");
                String custName = rs.getString("Customer_Name");
                String addr = rs.getString("Address");
                String pcode = rs.getString("Postal_Code");
                String ph = rs.getString("Phone");
                int divId = rs.getInt("Division_ID");
                int countryId = rs.getInt("Country_ID");
                String div = rs.getString("Division");
                String country = rs.getString("Country");
                Customer c = new Customer(custId, custName, addr, pcode, ph, divId, countryId, div, country);
                allCustomersOL.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return allCustomersOL;
    }

    /** This method inserts a customer via a SQL call to the database.
     @param customer The customer object to be inserted.
     @return Returns the rowsAffected as an int or the int 0.
     */
    public int insert(Customer customer) {
        try {
            String sql = "INSERT INTO CUSTOMERS VALUES (NULL, ?, ?, ?, ?, NULL, NULL, NULL, NULL, ?)";
            PreparedStatement ps = JDBC.connection.prepareStatement(sql);
            ps.setString(1, customer.getCustomerName());
            ps.setString(2, customer.getAddress());
            ps.setString(3, customer.getPostalCode());
            ps.setString(4, customer.getPhone());
            ps.setInt(5, customer.getDivisionId());
            int rowsAffected = ps.executeUpdate();
            return rowsAffected;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    /*public static int insert(String fruitName, int colorId) throws SQLException {
        String sql = "INSERT INTO FRUITS (Fruit_Name, Color_ID) VALUES (?, ?)";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1, fruitName);
        ps.setInt(2, colorId);
        int rowsAffected = ps.executeUpdate();  // executeUpdate() does the insert into the DB table and assigns the number of rows
        // affected to the variable rowsAffected.
        return rowsAffected;
    }*/

    /** This method selects a customer via a SQL call to the database.
     @param customer The customer object to be selected.
     */
    public void select(Customer customer) {

    }

    /** This method updates a customer via a SQL call to the database.
     @param customer The customer object to be updated.
     @return Returns the rowsAffected as an int or the int 0.
     */
    @Override
    public int update(Customer customer) {
        try {
            String sql = "UPDATE CUSTOMERS SET Customer_Name = ?, Address = ?, Division_ID = ?, Postal_Code = ?, Phone = ? WHERE Customer_ID = ?";
            PreparedStatement ps = JDBC.connection.prepareStatement(sql);
            ps.setString(1, customer.getCustomerName());
            ps.setString(2, customer.getAddress());
            ps.setInt(3, customer.getDivisionId());
            ps.setString(4, customer.getPostalCode());
            ps.setString(5, customer.getPhone());
            ps.setInt(6, customer.getCustomerId());
            int rowsAffected = ps.executeUpdate();
            return rowsAffected;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return 0;
        }
    }

    /** This method updates a customer via a SQL call to the database.
     @param CustomerID The customerID int to be matched in the database to do the update.
     @param CustomerName The customer name string to be matched in the database to do the update.
     @return Returns the rowsAffected as an int or the int 0.
     */
    public static int update(int CustomerID, String CustomerName) throws SQLException {  // this function updates the customer name
        String sql = "UPDATE CUSTOMERS SET Customer_Name = ? WHERE Customer_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1, CustomerName);
        ps.setInt(2, CustomerID);
        int rowsAffected = ps.executeUpdate();
        System.out.println("The number of rows affected from the update() call is " + rowsAffected);
        return rowsAffected;
    }

    /** This method deletes an appointment object via a SQL call to the database.
     @param customer The customer object with a matching customerID in the database to be deleted.
     @return Returns the rowsAffected as an int or the int 0. */
    @Override
    public int delete(Customer customer) {
        try {
            String sql = "DELETE FROM CUSTOMERS WHERE Customer_ID = ?";
            PreparedStatement ps = JDBC.connection.prepareStatement(sql);
            ps.setInt(1, customer.getCustomerId());
            int rowsAffected = ps.executeUpdate();
            System.out.println("The number of rows affected from the delete() call is " + rowsAffected + ".  -- delete(int CustomerID) called in CustomerDAOImpl.java");

            return rowsAffected;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return 0;
        }
    }

    /** This method deletes an appointment object via a SQL call to the database.
     @param CustomerID The customerId int with a matching customerID in the database to be deleted.
     @return Returns the rowsAffected as an int. */
    public int delete(int CustomerID) throws SQLException {
        String sql = "DELETE FROM CUSTOMERS WHERE Customer_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setInt(1, CustomerID);
        int rowsAffected = ps.executeUpdate();
        System.out.println("The number of rows affected from the delete() call is " + rowsAffected + ".  -- delete(int CustomerID) called in CustomerDAOImpl.java");

        return rowsAffected;
    }

}
