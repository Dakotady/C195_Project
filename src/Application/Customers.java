package Application;

import java.sql.Timestamp;

public class Customers {

    int customerID;
    String customerName;
    String address;
    String postalCode;
    String phoneNum;
    Timestamp create;
    String createdBy;
    Timestamp lastUpdated;
    String lastUpdatedBy;
    int divisionID;

    /**
     * This creates the reference object Customers.
     * @param customerID
     * @param customerName
     * @param address
     * @param postalCode
     * @param phoneNum
     * @param create
     * @param createdBy
     * @param lastUpdated
     * @param lastUpdatedBy
     * @param divisionID
     */
    public Customers(int customerID, String customerName, String address, String postalCode, String phoneNum, Timestamp create, String createdBy, Timestamp lastUpdated, String lastUpdatedBy, int divisionID){

        this.customerID = customerID;
        this.customerName = customerName;
        this.address = address;
        this.postalCode = postalCode;
        this.phoneNum = phoneNum;
        this.create = create;
        this.createdBy = createdBy;
        this.lastUpdated = lastUpdated;
        this.lastUpdatedBy = lastUpdatedBy;
        this.divisionID = divisionID;

    }

    /**
     * This gets the customerID.
     * @return
     */
    public int getCustomerID() {
        return customerID;
    }

    /**
     * This sets the customerID.
     * @param customerID
     */
    public void setCustomerID(int customerID) {
            this.customerID = customerID;
    }

    /**
     * This gets the customerName.
     * @return
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     * This sets the customerName.
     * @param customerName
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    /**
     * This gets the address.
     * @return
     */
    public String getAddress() {
        return address;
    }

    /**
     * This sets the address.
     * @param address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * This gets the postalCode.
     * @return
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * This sets the postalCode
     * @param postalCode
     */
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    /**
     * This gets the phoneNumber.
     * @return
     */
    public String getPhoneNum() {
        return phoneNum;
    }

    /**
     * This sets teh PhoneNumber.
     * @param phoneNum
     */
    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    /**
     * This gets the createDate.
     * @return
     */
    public Timestamp getCreate() {
        return create;
    }

    /**
     * This sets the CreateDate.
     * @param create
     */
    public void setCreate(Timestamp create) {
        this.create = create;
    }

    /**
     * This gets who created the customer.
     * @return
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * this sets who created the customer.
     * @param createdBy
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * This gets the LastUpdated Date.
     * @return
     */
    public Timestamp getLastUpdated() {
        return lastUpdated;
    }

    /**
     * This sets the LastUpdated Date.
     * @param lastUpdated
     */
    public void setLastUpdated(Timestamp lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    /**
     * This gets who last updated the customer.
     * @return
     */
    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    /**
     * This sets who last updated the customer.
     * @param lastUpdatedBy
     */
    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    /**
     * This gets the divisionID.
     * @return
     */
    public int getDivisionID() {
        return divisionID;
    }

    /**
     * This sets the divisionID.
     * @param divisionID
     */
    public void setDivisionID(int divisionID) {
        this.divisionID = divisionID;
    }
}
