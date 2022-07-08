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

    /**
     @ return the country ID
     */
    public int getCountryId() {
        return countryId;
    }

    /**
     @ return the division
     */
    public String getDivision() {
        return division;
    }

    /**
     @ return the country
     */
    public String getCountry() {
        return country;
    }

    /**
     @ return the customer ID
     */
    public int getCustomerId() {
        return customerId;
    }

    /**
     @ return the customer name
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     @ return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     @ return the postal code
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     @ return the phone number
     */
    public String getPhone() {
        return phone;
    }

    /**
     @ return the division ID
     */
    public int getDivisionId() {
        return divisionId;
    }

    /**
     @ return the String of customer ID and customer name
     */
    @Override
    public String toString(){
        //return String.valueOf(customerId) + " - " + (customerName);  // String.valueOf is deemed unnecessary
        return (customerId) + " - " + (customerName);
    }
}