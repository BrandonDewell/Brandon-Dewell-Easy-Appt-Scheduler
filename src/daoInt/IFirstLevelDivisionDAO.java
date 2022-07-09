package daoInt;

import daoModel.FirstLevelDivision;
import javafx.collections.ObservableList;

/** This method is an Interface that provides a method to be used in its associated FirstLevelDivision implementation file. */
public interface IFirstLevelDivisionDAO {

    /** A method signature to get an observable list of first level divisions.
     * @param countryId The country ID that matches the first level division in the observable list and is set to the State/Province combo box.
     * @return allFirstLevelDivisionsOL Returns an observable list.
     */
    ObservableList<FirstLevelDivision> getAllFirstLevelDivisionsOL(int countryId);
}