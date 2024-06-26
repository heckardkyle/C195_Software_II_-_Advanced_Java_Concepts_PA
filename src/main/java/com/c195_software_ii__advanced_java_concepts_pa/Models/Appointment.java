package com.c195_software_ii__advanced_java_concepts_pa.Models;

import java.time.ZonedDateTime;

/**
 * Class for creating Appointment objects.
 * Appointment objects are used for displaying in a tableview on the appointments screen for user to view, as well
 * as extensively used for Reports. Appointment objects require a customerID from the Customer Class, a userID
 * from the User Class, and a contactID from the Contact Class.
 * Appointment objects will be created as they are retrieved from the database and can be used to update the
 * database as well as be deleted from the database. The appointments for a specific customer must be deleted
 * before said Customer can be deleted.
 *
 * @author Kyle Heckard
 * @version 1.0
 * @see User
 * @see Customer
 * @see Contact
 * @see com.c195_software_ii__advanced_java_concepts_pa.DAO.AppointmentDBImpl
 */
public class Appointment {

    /* --Members-- */

    private int           appointmentID;
    private String        title;
    private String        description;
    private String        location;
    private String        type;
    private ZonedDateTime dateTimeStart;
    private ZonedDateTime dateTimeEnd;
    private int           customerID;
    private int           userID;
    private int           contactID;

    /* --Constructors-- */

    /**
     * Default Constructor for Appointment objects
     *
     * @param appointmentID the appointmentID to be set
     * @param title         the appointment title to be set
     * @param description   the appointment description to be set
     * @param location      the location to be set
     * @param type          the appointment type to be set
     * @param dateTimeStart the appointment start time to be set
     * @param dateTimeEnd   the appointment end time to be set
     * @param customerID    the associated customerID to be set
     * @param userID        the associated userID to be set
     * @param contactID     the associated contactID to be set
     */
    public Appointment(int appointmentID, String title, String description, String location, String type,
                       ZonedDateTime dateTimeStart, ZonedDateTime dateTimeEnd, int customerID, int userID, int contactID) {

        this.appointmentID = appointmentID;
        this.title         = title;
        this.description   = description;
        this.location      = location;
        this.type          = type;
        this.dateTimeStart = dateTimeStart;
        this.dateTimeEnd   = dateTimeEnd;
        this.customerID    = customerID;
        this.userID        = userID;
        this.contactID     = contactID;
    }

    /* --Getters-- */

    /** Gets the appointmentID.
     *  @return <code>appointmentID</code> */
    public int getAppointmentID() { return appointmentID; }

    /** Gets the appointment title.
     *  @return <code>title</code> */
    public String getTitle() { return title; }

    /** Gets the appointment description.
     *  @return <code>description</code> */
    public String getDescription() { return description; }

    /** Gets the appointment location.
     *  @return <code>location</code> */
    public String getLocation() { return location; }

    /** Gets the appointment type.
     *  @return <code>type</code> */
    public String getType() { return type; }

    /** Gets the appointment startTime.
     *  @return <code>startTime</code> */
    public ZonedDateTime getDateTimeStart() { return dateTimeStart; }

    /** Gets the appointment endTime.
     *  @return <code>endTime</code> */
    public ZonedDateTime getDateTimeEnd() { return dateTimeEnd; }

    /** Gets the customerID associated with the appointment.
     *  @return <code>customerID</code> */
    public int getCustomerID() { return customerID; }

    /** Gets the userID associated with the appointment.
     *  @return <code>userID</code> */
    public int getUserID() { return userID; }

    /** Gets the contactID associated with the appointment.
     *  @return <code>contactID</code> */
    public int getContactID() { return contactID; }

    /* --Setters-- */

    /** Sets the appointmentID.
     *  @param appointmentID the appointmentID to be set */
    public void setAppointmentID(int appointmentID) { this.appointmentID = appointmentID; }

    /** Sets the appointment title.
     *  @param title the appointment title to be set */
    public void setTitle(String title) { this.title = title; }

    /** Sets the appointment description.
     *  @param description the appointment description to be set */
    public void setDescription(String description) { this.description = description; }

    /** Sets the appointment location.
     *  @param location the appointment location to be set */
    public void setLocation(String location) { this.location = location; }

    /** Sets the appointment type.
     *  @param type the appointment type to be set */
    public void setType(String type) { this.type = type; }

    /** Sets the appointment startTime.
     *  @param dateTimeStart the appointment startTime to be set */
    public void setDateTimeStart(ZonedDateTime dateTimeStart) { this.dateTimeStart = dateTimeStart; }

    /** Sets the appointment endTime.
     *  @param dateTimeEnd the appointment endTime to be set */
    public void setDateTimeEnd(ZonedDateTime dateTimeEnd) { this.dateTimeEnd = dateTimeEnd; }

    /** Sets the customerID associated with the appointment.
     *  @param customerID the customerID to associate with the appointment */
    public void setCustomerID(int customerID) { this.customerID = customerID; }

    /** Sets the userID associated with the appointment.
     *  @param userID the userID to associate with the appointment */
    public void setUserID(int userID) { this.userID = userID; }

    /** Sets the contactID associated with the appointment.
     *  @param contactID the contactID to associate with the appointment */
    public void setContactID(int contactID) { this.contactID = contactID; }
}
