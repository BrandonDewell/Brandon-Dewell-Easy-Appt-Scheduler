package daoInt;

import daoModel.Customer;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public interface ICustomerDAO {   // abstract means you cannot instantiate from them

    public ObservableList<Customer> getAllCustomersOL();

    public static int insert(Customer customer) throws SQLException  {    //watch JDBC webinar 5/24 7:28pm
        return 0;
    }

    public static void select(Customer customer) throws SQLException  {
    }

    public static int update(Customer customer) {
        return 0;
    }

    public static int delete(Customer customer) {
        return 0;
    }

}