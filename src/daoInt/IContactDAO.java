package daoInt;

import daoModel.Contact;
import javafx.collections.ObservableList;

public interface IContactDAO {

    ObservableList<Contact> getAllContactsOL();

}
