package daoInt;

import daoModel.Customer;

import java.sql.SQLException;

public interface CustomerDAO {
    public static void select(Customer customer) throws SQLException  // abstract means you cannot instantiate from them
    {

    }

    public static int insert(Customer customer) throws SQLException  //watch JDBC webinar 5/24 7:28pm
    {
        return 0;
    }

    public static int update(Customer customer) {
        return 0;
    }

    public static int delete(Customer customer) {
        return 0;
    }
}
