package lk.ijse.gdse66.POS_BackEnd.bo.custom;

import lk.ijse.gdse66.POS_BackEnd.bo.SuperBO;

import java.sql.Connection;
import java.sql.SQLException;

public interface QueryBO extends SuperBO {

    int getSumOrders(Connection connection) throws SQLException, ClassNotFoundException;

    int getItem(Connection connection) throws SQLException, ClassNotFoundException;

    int getCustomer(Connection connection) throws SQLException, ClassNotFoundException;
}
