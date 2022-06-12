package daoImpl;

import daoInt.ICustomerDAO;
import daoModel.Customer;
import helper.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerDAOImpl implements ICustomerDAO {  // write sql interactions and observable lists here

    public ObservableList<Customer> getAllCustomersOL(){

        ObservableList<Customer> allCustomersOL = FXCollections.observableArrayList();

        try{
            String sql = "SELECT Customer_ID, Customer_Name, Address, Postal_Code, Phone, Customers.Division_ID, Division, First_Level_Divisions.Country_ID, Country FROM Customers, First_Level_Divisions, Countries WHERE Customers.Division_ID = First_Level_Divisions.Division_ID AND First_Level_Divisions.Country_ID = Countries.Country_ID";
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
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return allCustomersOL;
    }



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

    public void select(Customer customer) {
    }

    @Override
    public int update(Customer customer) {
        try {
            String sql = "UPDATE CUSTOMERS SET Customer_Name = ?, Address = ?, Phone = ?, Postal_Code = ?, Division_ID = ? WHERE Customer_ID = ?";
            PreparedStatement ps = JDBC.connection.prepareStatement(sql);
            ps.setString(1, customer.getCustomerName());
            ps.setString(2, customer.getAddress());
            ps.setString(3, customer.getPostalCode());
            ps.setString(4, customer.getPhone());
            ps.setInt(5, customer.getDivisionId());
            ps.setInt(6, customer.getCustomerId());
            int rowsAffected = ps.executeUpdate();
            return rowsAffected;
        } catch (SQLException ex)
            {
                ex.printStackTrace();
                return 0;
            }
    }

    @Override
    public int delete(Customer customer) {  // TODO delete from appts first then customer. delete from appts where cust id = ?  delete from customers id = ?
        return 0;
    }

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
        System.out.println("The number of rows affected from the delete() call is " + rowsAffected + ".  -- delete(int CustomerID) called in CustomerDAOImpl.java");

        return rowsAffected;
    }


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
