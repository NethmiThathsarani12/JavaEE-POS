package lk.ijse.gdse66.POS_BackEnd.dao.custom;

import lk.ijse.gdse66.POS_BackEnd.dao.SuperDAO;

import java.sql.Connection;
import java.sql.SQLException;

public interface QueryDAO extends SuperDAO {

    int getSumOrders(Connection connection) throws SQLException, ClassNotFoundException;

    int getItem(Connection connection) throws SQLException, ClassNotFoundException;

    int getCustomer(Connection connection) throws SQLException, ClassNotFoundException;
}
