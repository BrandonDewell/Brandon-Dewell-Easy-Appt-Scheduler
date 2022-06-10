package daoInt;

import daoModel.Country;
import javafx.collections.ObservableList;

public interface ICountryDAO {

    ObservableList<Country> getAllCountriesOL();

}
