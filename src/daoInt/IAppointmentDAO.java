package daoInt;

import daoModel.Appointment;
import javafx.collections.ObservableList;

/** This method is an Interface that provides methods to be used in its associated Appointment implementation file. */
public interface IAppointmentDAO {

    /** A method signature to get an observable list of appointments. */
    public ObservableList<Appointment> getAllAppointmentsOL();

    /** A method signature to insert an appointment into the database. */
    public int insert(Appointment appointment);

    /** A method signature to select an appointment from the database. */
    public void select(Appointment appointment);

    /** A method signature to update an appointment in the database. */
    public int update(Appointment appointment);

    /** A method signature to delete an appointment from the database. */
    public int delete(Appointment appointment);

}