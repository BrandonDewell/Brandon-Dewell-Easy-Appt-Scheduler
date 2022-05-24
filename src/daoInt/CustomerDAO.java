package daoInt;

import java.sql.SQLException;

public interface CustomerDAO {
    public abstract void select() throws SQLException;
    public abstract int insert();
    public abstract int update();
    public abstract int delete();
}
