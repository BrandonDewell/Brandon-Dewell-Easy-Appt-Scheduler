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

    /** This method gets the user ID.
     @return Returns the user ID.
     */
    public int getUserId() {
        return userId;
    }

    /** This method gets the user name.
     @return Returns the user name.
     */
    public String getUserName() {
        return userName;
    }

    /** This method returns the password.
     @return Returns the password.
     */
    public String getPassword() {
        return password;
    }

    /**  This method changes the observable list User_ID combo box choices in the add/update appointment window to have both user ID and user name.
     @return Returns the String of user ID and user name in a string.
     */
    @Override
    public String toString(){
        //return String.valueOf(userId) + " - " + (userName);
        return (userId) + " - " + (userName);
    }

}
