package daoImpl;

import daoInt.FISqlSelAll;
import daoInt.IUserDAO;
import daoModel.User;
import helper.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAOImpl implements IUserDAO {
// TODO description of the lambda goes in the javadoc block here.
    @Override
    public ObservableList<User> getAllUsersOL() {

        ObservableList<User> usersOL = FXCollections.observableArrayList();

        try {
           // String sql = "SELECT * FROM USERS";  // I am replacing this with a lambda.  Not sure why as it seems simpler to just type it out as done here.
            FISqlSelAll sqlAll = s -> "SELECT * FROM " + s;  // TODO I used a lambda here, it works, but why even use it here?
            String sql = sqlAll.getSqlAll("USERS");
            System.out.println("Lambda sqlAll used.");
            PreparedStatement ps = JDBC.connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int userId = rs.getInt("User_ID");
                String userName = rs.getString("User_Name");
                String pw = rs.getString("Password");
                User u = new User(userId, userName, pw);
                usersOL.add(u);
            }
        }
        catch (SQLException ex){
            ex.printStackTrace();
        }

        return usersOL;
    }

}


