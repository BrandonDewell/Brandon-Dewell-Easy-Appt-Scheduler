package daoImpl;

import daoInt.IContactDAO;
import daoModel.Contact;
import helper.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ContactDAOImpl implements IContactDAO {

    @Override
    public ObservableList<Contact> getAllContactsOL() {

        ObservableList<Contact> contactsOL = FXCollections.observableArrayList();

        try {
            String sql = "SELECT * FROM CONTACTS";
            PreparedStatement ps = JDBC.connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int contactID = rs.getInt("Contact_ID");
                String contactName = rs.getString("Contact_Name");
                String email = rs.getString("Email");
                Contact c = new Contact(contactID, contactName, email);
                contactsOL.add(c);
            }
        }
        catch (SQLException ex){
            ex.printStackTrace();
        }

        return contactsOL;
    }
}
