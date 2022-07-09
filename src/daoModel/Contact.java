package daoModel;

/** This class manipulates contacts. */
public class Contact {

    private int contactId;
    private String contactName;
    private String email;

    /** This method is a constructor for contacts.
     @param contactId The contact ID to be constructed.
     @param contactName The contact name to be constructed.
     @param email The email address to be constructed.
     */
    public Contact(int contactId, String contactName, String email) {
        this.contactId = contactId;
        this.contactName = contactName;
        this.email = email;
    }

    /** This method gets the contact ID.
     @return Returns the contact ID.
     */
    public int getContactId() {
        return contactId;
    }

    /** This method gets the contact name.
     @return Returns the contact name.
     */
    public String getContactName() {
        return contactName;
    }

    /** This method gets the email address.
     @return Returns the email address.
     */
    public String getEmail() {
        return email;
    }

    /**  This method changes the observable list Contact combo box choices in the add/update appointment window to have both contact ID and contact name.
     @return Returns the string of contactId and contactName as a string.
     */
    @Override
    public String toString(){
        //return String.valueOf(contactId) + " - " + (contactName);
        return (contactId) + " - " + (contactName);
    }
}
