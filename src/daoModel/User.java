package daoModel;

/** This class manipulates users. */
public class User {

    private int userId;
    private String userName;
    private String password;

    /** This method is a constructor for users.
     @param userId The user ID to be constructed.
     @param userName The user name to be constructed.
     @param password The password to be constructed.
     */
    public User(int userId, String userName, String password) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
    }

    /**
     @ return the user ID
     */
    public int getUserId() {
        return userId;
    }

    /**
     @ return the user name
     */
    public String getUserName() {
        return userName;
    }

    /**
     @ return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     @ return the String of user ID and user name
     */
    @Override
    public String toString(){
        //return String.valueOf(userId) + " - " + (userName);
        return (userId) + " - " + (userName);
    }

}
