package daoImpl;

import daoInt.IUserDAO;
import daoModel.User;
import helper.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAOImpl implements IUserDAO {

    @Override
    public ObservableList<User> getAllUsers() {

        ObservableList<User> usersOL = FXCollections.observableArrayList();

        try {
            String sql = "SELECT * FROM USERS";
            PreparedStatement ps = JDBC.connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int userId = rs.getInt("User_ID");
                String userName = rs.getString("User_Name");  // I only got customer id and customer name and excluded lots
                // of other columns in the while loop walking through all the customer data.
                String pw = rs.getString("Password");
                User u = new User(userId, userName, pw);
                usersOL.add(u);
            }
            // System.out.println(usersOL.get(1));
        }
        catch (SQLException ex){
            ex.printStackTrace();
        }

        return usersOL;
    }

}
