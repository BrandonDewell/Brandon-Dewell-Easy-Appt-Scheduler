package daoImpl;

import daoInt.ICustomerDAO;
import daoModel.Customer;
import helper.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerDAOImpl implements ICustomerDAO {  // write sql things here

    public static int insert(Customer customer) throws SQLException {
        //String sql = "INSERT INTO CUSTOMERS (Customer_Name, Address, Postal_Code, Phone, Division_ID) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";  // 3/15 12:13pm Question
        // marks are bind variables starting at index 1.
        String sql = "INSERT INTO CUSTOMERS VALUES (NULL, ?, ?, ?, ?, NULL, NULL, NULL, NULL, ?)";  // have to put in the first NULL to let mysql handle inputting
        // values for the Customer_ID.
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);  // anytime there is a preparedStatement() called in a method, you must throw a SQLException
        // in the header/signature of the method that calls the preparedStatement().
        ps.setString(1, customer.getCustomerName());
        ps.setString(2, customer.getAddress());
        ps.setString(3, customer.getPostalCode());
        ps.setString(4, customer.getPhone());
        ps.setInt(9, customer.getDivisionId());
        int rowsAffected = ps.executeUpdate();
        return rowsAffected;
    }

    /*public static int insert(String CustomerName, String Address, String PostalCode, String Phone, Timestamp CreateDate, String CreatedBy, Timestamp LastUpdate,
                                String LastUpdatedBy, int DivisionID) throws SQLException {
        //String sql = "INSERT INTO CUSTOMERS (Customer_Name, Address, Postal_Code, Phone, Create_Date, Created_By, Last_Update, Last_Updated_By, Division_ID)
                                                VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";  // 3/15 12:13pm Question marks are bind variables starting at index 1.
        String sql = "INSERT INTO CUSTOMERS VALUES (NULL, ?, ?, ?, ?, ?, ?, ?, ?, ?)";  // have to put in NULL to let mysql handle inputting values for the Customer_ID.

        PreparedStatement ps = JDBC.connection.prepareStatement(sql);  // anytime there is a preparedStatement() called in a method, you must throw a SQLException
        // in the header/signature of the method that calls the preparedStatement().
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
    }*/

    public static void select(Customer customer) throws SQLException {
        String sql = "SELECT * FROM CUSTOMERS";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            int customerID = rs.getInt("Customer_ID");
            String customerName = rs.getString("Customer_Name");  // i only got customer id and customer name and excluded lots of other columns in the
            // while loop walking through all the customer data.
            System.out.print(customerID + " | ");  // use print instead of println if I want to print customer id and customer name info for each customer on
            // multiple lines.  Malcom's JDBC webinar at 1hr 7mins.
            System.out.print(customerName + "\n");
            customer.getAllCustomers();
        }
    }

    /* // Overloaded select statement
    public static void select(String customerName) throws SQLException {
        String sql = "SELECT * FROM CUSTOMERS WHERE Customer_Name = ?";  // added the WHERE Customer_Name = ? part to the SELECT statement.
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

    // A different overloaded select statement that prints out a little bit more data.
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
    }*/

    public static int update(int CustomerID, String CustomerName) throws SQLException {  // this function updates the customer name
        String sql = "UPDATE CUSTOMERS SET Customer_Name = ? WHERE Customer_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1, CustomerName);
        ps.setInt(2, CustomerID);
        int rowsAffected = ps.executeUpdate();
        System.out.println("The number of rows affected from the update() call is " + rowsAffected);
        return rowsAffected;
    }

    public static int delete(int CustomerID) throws SQLException {
        String sql = "DELETE FROM CUSTOMERS WHERE Customer_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setInt(1, CustomerID);
        int rowsAffected = ps.executeUpdate();
        System.out.println("The number of rows affected from the delete() call is " + rowsAffected);
        return rowsAffected;
    }




    // 5/25 11:03am this was from the old CustomersQuery.java in the helper package.  I deleted this CustomersQuery.java class to use the DAOImpl package instead.


   /* public class query {  // from Carolyn's video titled C195 Organizing your code 2021 at 9 mins 30 secs.
        private static String query;
        private static Statement stmt;
        private static ResultSet result;

        public static void makeQuery(String q){
            query = q;
            try{
                stmt = conn.createStatement();
                //determine query execution
                if(query.toLowerCase().startsWith("select"))
                    result = stmt.executeQuery(q);
                if(query.toLowerCase().startsWith("delete") || query.toLowerCase().startsWith("insert") || query.toLowerCase().startsWith("update"))
                    result = stmt.executeUpdate(q);
            }
            catch (Exception e){
                System.out.println("Error: " + e.getMessage());
            }
            public static ResultSet getResult() {
                return result;
            }
        }
    }*/
}
