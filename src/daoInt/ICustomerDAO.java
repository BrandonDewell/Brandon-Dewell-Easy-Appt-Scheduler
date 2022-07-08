package daoInt;

import daoModel.Customer;
import javafx.collections.ObservableList;

/** This method is an Interface that provides methods to be used in its associated Customer implementation file. */
public interface ICustomerDAO {   // abstract means you cannot instantiate from them

    /** A method signature to get an observable list of customers. */
    public ObservableList<Customer> getAllCustomersOL();

    /** A method signature to insert a customer into the database. */
    public int insert(Customer customer);

    /** A method signature to select a customer from the database. */
    public void select(Customer customer);

    /** A method signature to update a customer in the database. */
    public int update(Customer customer);

    /** A method signature to delete a customer from the database. */
    public int delete(Customer customer);

}