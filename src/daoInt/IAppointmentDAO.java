package daoInt;

import daoModel.Appointment;
import javafx.collections.ObservableList;

public interface IAppointmentDAO {

    public ObservableList<Appointment> getAllAppointmentsOL();

    public int insert(Appointment appointment);

    public void select(Appointment appointment);

    public int update(Appointment appointment);

    public int delete(Appointment appointment);

    //public int delete(int customer_Id);

}