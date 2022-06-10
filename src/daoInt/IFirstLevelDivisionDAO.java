package daoInt;

import daoModel.FirstLevelDivision;
import javafx.collections.ObservableList;

public interface IFirstLevelDivisionDAO {

    ObservableList<FirstLevelDivision> getAllFirstLevelDivisionsOL(int countryId);

}
