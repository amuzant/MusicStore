package dao;
import java.sql.SQLException;

public interface DaoInterface <T>{
    public void add(T entity)  throws SQLException;

    public T read(String id) throws SQLException;

    public void delete(T entity) throws SQLException;

    public void update(T entity) throws SQLException;
}