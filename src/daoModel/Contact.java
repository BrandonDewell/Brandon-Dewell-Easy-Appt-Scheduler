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

    /**
     @ return the contact ID
     */
    public int getContactId() {
        return contactId;
    }

    /**
     @ return the contact name
     */
    public String getContactName() {
        return contactName;
    }

    /**
     @ return the email address
     */
    public String getEmail() {
        return email;
    }

    /**
     @ return the string of contactId and contactName
     */
    @Override
    public String toString(){
        //return String.valueOf(contactId) + " - " + (contactName);
        return (contactId) + " - " + (contactName);
    }
}
