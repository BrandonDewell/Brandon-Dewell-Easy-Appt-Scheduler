package daoInt;

import daoModel.User;
import javafx.collections.ObservableList;

public interface IUserDAO {

    ObservableList<User> getAllUsers();

}
