package daoInt;

import daoModel.Contact;
import javafx.collections.ObservableList;

/** This method is an Interface that provides a method to be used in its associated Contact implementation file. */
public interface IContactDAO {

    /** A method signature to get an observable list of contacts.
     * @return allContactsOL Returns an observable list.
     */
    ObservableList<Contact> getAllContactsOL();
}
