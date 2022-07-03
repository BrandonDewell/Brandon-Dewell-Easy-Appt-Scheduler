package daoModel;

public class Customer {
    // class variables/fields
    private int customerId;
    private int divisionId;
    private int countryId;
    private String customerName;
    private String address;
    private String postalCode;
    private String phone;
    private String division;
    private String country;


    // constructor/methods
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

    public int getCountryId() {
        return countryId;
    }

    public String getDivision() {
        return division;
    }

    public String getCountry() {
        return country;
    }

    public int getCustomerId() {
        return customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getAddress() {
        return address;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getPhone() {
        return phone;
    }

    public int getDivisionId() {
        return divisionId;
    }

    @Override
    public String toString(){
        return String.valueOf(customerId) + " - " + (customerName);
    }

}