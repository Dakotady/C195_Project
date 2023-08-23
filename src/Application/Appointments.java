package Application;

import java.sql.Timestamp;


public class Appointments {

    int appointmentID;
    String title;
    String description;
    String location;
    String type;
    Timestamp start;
    Timestamp end;
    Timestamp create;
    String createdBy;
    Timestamp lastUpdated;
    String lastUpdatedBy;
    int customerID;
    int userID;
    int contactID;

    /**
     * This creates the reference object Appointments.
     * @param appointmentID
     * @param title
     * @param description
     * @param location
     * @param type
     * @param start
     * @param end
     * @param create
     * @param createdBy
     * @param lastUpdated
     * @param lastUpdatedBy
     * @param customerID
     * @param userID
     * @param contactID
     */
    public Appointments(int appointmentID, String title, String description, String location, String type, Timestamp start, Timestamp end, Timestamp create, String createdBy, Timestamp lastUpdated, String lastUpdatedBy, int customerID, int userID, int contactID){

        this.appointmentID = appointmentID;
        this.title = title;
        this.description = description;
        this.location = location;
        this.type = type;
        this.start = start;
        this.end = end;
        this.create = create;
        this.createdBy = createdBy;
        this.lastUpdated = lastUpdated;
        this.lastUpdatedBy = lastUpdatedBy;
        this.customerID = customerID;
        this.userID = userID;
        this.contactID = contactID;

    }

    /**
     * this gets the appointmentID.
     * @return
     */
    public int getAppointmentID() {
        return appointmentID;
    }

    /**
     * This sets the appointmentID.
     * @param appointmentID
     */
    public void setAppointmentID(int appointmentID) {
            this.appointmentID = appointmentID;
    }

    /**
     * This gets the title.
     * @return
     */
    public String getTitle() {
        return title;
    }

    /**
     * This sets the title.
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * This gets the description.
     * @return
     */
    public String getDescription() {
        return description;
    }

    /**
     * This sets the description.
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * This gets the location.
     * @return
     */
    public String getLocation() {
        return location;
    }

    /**
     * This sets the location.
     * @param location
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * This gets the type.
     * @return
     */
    public String getType() {
        return type;
    }

    /**
     * This sets the type.
     * @param type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * This gets the start date.
     * @return
     */
    public Timestamp getStart() {
        return start;
    }

    /**
     * This sets the start date.
     * @param start
     */
    public void setStart(Timestamp start) {
        this.start = start;
    }

    /**
     * This gets the end date.
     * @return
     */
    public Timestamp getEnd() {
        return end;
    }

    /**
     * This sets the end date.
     * @param end
     */
    public void setEnd(Timestamp end) {
        this.end = end;
    }

    /**
     * This gets the create date.
     * @return
     */
    public Timestamp getCreate() {
        return create;
    }

    /**
     * This sets the create date.
     * @param create
     */
    public void setCreate(Timestamp create) {
        this.create = create;
    }

    /**
     * This gets who created.
     * @return
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * This sets who created.
     * @param createdBy
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * This gets lastUpdated date.
     * @return
     */
    public Timestamp getLastUpdated() {
        return lastUpdated;
    }

    /**
     * This sets the lastUpdated date.
     * @param lastUpdated
     */
    public void setLastUpdated(Timestamp lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    /**
     * This gets who lastUpdated.
     * @return
     */
    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    /**
     * This sets who lastUpdated.
     * @param lastUpdatedBy
     */
    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    /**
     * This gets customerID.
     * @return
     */
    public int getCustomerID() {
        return customerID;
    }

    /**
     * This sets customerID.
     * @param customerID
     */
    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    /**
     * This gets userID.
     * @return
     */
    public int getUserID() {
        return userID;
    }

    /**
     * This sets userID.
     * @param userID
     */
    public void setUserID(int userID) {
        this.userID = userID;
    }

    /**
     * This gets the contactID.
     * @return
     */
    public int getContactID() {
        return contactID;
    }

    /**
     * This sets contactID.
     * @param contactID
     */
    public void setContactID(int contactID) {
        this.contactID = contactID;
    }
}
