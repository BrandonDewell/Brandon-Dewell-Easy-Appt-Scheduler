package daoInt;

import daoModel.User;
import javafx.collections.ObservableList;

/** This method is an Interface that provides a method to be used in its associated User implementation file. */
public interface IUserDAO {

    /** A method signature to get an observable list of users. */
    ObservableList<User> getAllUsersOL();

}
