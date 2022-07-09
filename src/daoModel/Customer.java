package daoModel;

/** This class manipulates customers. */
public class Customer {

    private int customerId;
    private int divisionId;
    private int countryId;
    private String customerName;
    private String address;
    private String postalCode;
    private String phone;
    private String division;
    private String country;

    /** This method is a constructor for Customers.
     @param customerId The customer ID to be constructed.
     @param customerName The customer name to be constructed.
     @param address The address to be constructed.
     @param postalCode The postal code to be constructed.
     @param phone The phone number to be constructed.
     @param divisionId The division ID to be constructed.
     @param countryId The country ID to be constructed.
     @param division The division to be constructed.
     @param country The country to be constructed.
     */
    public Customer(int customerId, String customerName, String address, String postalCode, String phone, int divisionId, int countryId, String division, String country) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.address = address;
        this.postalCode = postalCode;
        this.phone = phone;
        this.divisionId = divisionId;
        this.countryId = countryId;
        this.division = division;
        this.country = country;
    }

    /** This method gets the country ID.
     @return Returns the country ID.
     */
    public int getCountryId() {
        return countryId;
    }

    /** This method gets the division.
     @return Returns the division.
     */
    public String getDivision() {
        return division;
    }

    /** This method gets the country.
     @return Returns the country.
     */
    public String getCountry() {
        return country;
    }

    /** This method gets the customer ID.
     @return int Returns the customer ID.
     */
    public int getCustomerId() {
        return customerId;
    }

    /** This method gets the customer name.
     @return Returns the customer name.
     */
    public String getCustomerName() {
        return customerName;
    }

    /** This method gets the address.
     @return Returns the address.
     */
    public String getAddress() {
        return address;
    }

    /** This method gets the postal code.
     @return Returns the postal code.
     */
    public String getPostalCode() {
        return postalCode;
    }

    /** This method gets the phone number.
     @return Returns the phone number.
     */
    public String getPhone() {
        return phone;
    }

    /** This method gets the division ID.
     @return Returns the division ID.
     */
    public int getDivisionId() {
        return divisionId;
    }

    /**  This method changes the observable list Customer_ID combo box choices in the add/update appointment window to have both customer ID and customer name.
     @return Returns the String of customer ID and customer name in a string.
     */
    @Override
    public String toString(){
        return (customerId) + " - " + (customerName);
    }
}