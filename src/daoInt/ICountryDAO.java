package daoInt;

import daoModel.Country;
import javafx.collections.ObservableList;

/** This method is an Interface that provides a method to be used in its associated Country implementation file. */
public interface ICountryDAO {

    /** A method signature to get an observable list of countries. */
    ObservableList<Country> getAllCountriesOL();

}
