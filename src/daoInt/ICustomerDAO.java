package daoInt;

import daoModel.Customer;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public interface ICustomerDAO {   // abstract means you cannot instantiate from them

    public ObservableList<Customer> getAllCustomersOL();

    public  int insert(Customer customer);

    public  void select(Customer customer);

    public int update(Customer customer);

    public  int delete(Customer customer);

}