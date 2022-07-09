package daoInt;

import daoModel.Appointment;
import javafx.collections.ObservableList;

/** This method is an Interface that provides methods to be used in its associated Appointment implementation file. */
public interface IAppointmentDAO {

    /** A method signature to get an observable list of appointments.
     *  @return Returns an observable list.
     */
    ObservableList<Appointment> getAllAppointmentsOL();

    /** A method signature to insert an appointment into the database.
     * @param appointment An appointment object to be inserted.
     *  @return Returns an the number of rows affected or returns 0.
     */
    int insert(Appointment appointment);

    /** A method signature to select an appointment from the database.
     * @param appointment An appointment object to be selected. */
    void select(Appointment appointment);

    /** A method signature to update an appointment in the database.
     * @param appointment An appointment object to be updated.
     *  @return Returns 0.
     */
    int update(Appointment appointment);

    /** A method signature to delete an appointment from the database.
     * @param appointment An appointment object to be deleted.
     *  @return Returns 0.
     */
    int delete(Appointment appointment);

}