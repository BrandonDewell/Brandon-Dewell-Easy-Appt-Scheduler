package daoInt;

import daoModel.Customer;
import javafx.collections.ObservableList;

/** This method is an Interface that provides methods to be used in its associated Customer implementation file. */
public interface ICustomerDAO {   // abstract means you cannot instantiate from them

    /** A method signature to get an observable list of customers.
     *  @return Returns an observable list.
     */
    ObservableList<Customer> getAllCustomersOL();

    /** A method signature to insert a customer into the database.
     * @param customer A customer object to be inserted into the database.
     * @return rowsAffected or 0 Returns the number of rows affected or 0.
     */
    int insert(Customer customer);

    /** A method signature to select a customer from the database.
     * @param customer A customer object to be selected.
     */
    void select(Customer customer);

    /** A method signature to update a customer in the database.
     * @param customer A customer object to be inserted into the database.
     * @return rowsAffected or 0 Returns the number of rows affected or 0.
     */
    int update(Customer customer);

    /** A method signature to delete a customer from the database.
     * @param customer A customer object ot be deleted from the database.
     * @return rowsAffected or 0 Returns the number of rows affected or 0.
     */
    int delete(Customer customer);

}