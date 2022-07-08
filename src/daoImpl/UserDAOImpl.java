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

/** This method implements an Interface and provides a method to do a SQL call to the database. */
public class UserDAOImpl implements IUserDAO {

    /** This method gets an Observable List via a SQL call to the database. A lambda expression is used to do a SQL call to the database on the USERS table.
     @return Returns the usersOL observable list of User objects.
     */
    @Override
    public ObservableList<User> getAllUsersOL() {

        ObservableList<User> usersOL = FXCollections.observableArrayList();

        try {
           // String sql = "SELECT * FROM USERS";  // I am replacing this with a lambda.
            FISqlSelAll sqlAll = s -> "SELECT * FROM " + s;
            String sql = sqlAll.getSqlAll("USERS");
           // System.out.println("Lambda sqlAll used.");
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


