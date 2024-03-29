package lk.ijse.gdse66.POS_BackEnd.dao.custom;


import lk.ijse.gdse66.POS_BackEnd.dao.CrudDAO;
import lk.ijse.gdse66.POS_BackEnd.entity.Order;

import java.sql.Connection;
import java.sql.SQLException;

public interface OrderDAO extends CrudDAO<Order, String, Connection> {

    String generateNewOrderId(Connection connection) throws SQLException, ClassNotFoundException;

    boolean ifOrderExist(String oid, Connection connection) throws SQLException, ClassNotFoundException;

//    ObservableList<OrderDetails> getAllDetails(Connection connection) throws SQLException, ClassNotFoundException;

    boolean mangeItems(int qty, String code, Connection connection) throws SQLException, ClassNotFoundException;


}