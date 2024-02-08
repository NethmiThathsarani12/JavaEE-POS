package lk.ijse.gdse66.POS_BackEnd.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public interface DAO<T, ID> extends SuperDAO{

    ArrayList<T> getAll(Connection connection) throws SQLException, ClassNotFoundException;

    boolean save(T dto, Connection connection) throws SQLException, ClassNotFoundException;

    boolean update(T dto, Connection connection) throws SQLException, ClassNotFoundException;

    ArrayList<T> searchId(String id, Connection connection) throws SQLException, ClassNotFoundException;

    boolean delete(ID id, Connection connection) throws SQLException, ClassNotFoundException;

    String generateNewID(Connection connection) throws SQLException, ClassNotFoundException;
}
