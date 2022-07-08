package daoInt;

import daoModel.FirstLevelDivision;
import javafx.collections.ObservableList;

/** This method is an Interface that provides a method to be used in its associated FirstLevelDivision implementation file. */
public interface IFirstLevelDivisionDAO {

    /** A method signature to get an observable list of first level divisions. */
    ObservableList<FirstLevelDivision> getAllFirstLevelDivisionsOL(int countryId);

}
