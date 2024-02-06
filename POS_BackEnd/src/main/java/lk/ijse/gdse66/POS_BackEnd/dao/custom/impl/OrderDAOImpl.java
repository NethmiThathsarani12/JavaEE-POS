package lk.ijse.gdse66.POS_BackEnd.dao.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.gdse66.POS_BackEnd.dao.CrudUtil;
import lk.ijse.gdse66.POS_BackEnd.dao.custom.OrderDAO;
import lk.ijse.gdse66.POS_BackEnd.entity.OrdersEntity;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderDAOImpl implements OrderDAO {
    @Override
    public boolean add(OrdersEntity ordersEntity, Connection connection) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate(connection, "INSERT INTO orders VALUES (?,?,?,?,?,?)",
                ordersEntity.getOid(),
                ordersEntity.getDate(),
                ordersEntity.getCustomerID(),
                ordersEntity.getTotal(),
                ordersEntity.getSubTotal(),
                ordersEntity.getDiscount());
    }

    @Override
    public ObservableList<OrdersEntity> getAll(Connection connection) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.executeQuery(connection, "SELECT * FROM orders");

        ObservableList<OrdersEntity> obList = FXCollections.observableArrayList();

        while (resultSet.next()) {
            OrdersEntity ordersEntity = new OrdersEntity(
                    resultSet.getString(1),
                    resultSet.getDate(2),
                    resultSet.getString(3),
                    resultSet.getDouble(4),
                    resultSet.getDouble(5),
                    resultSet.getDouble(6)
            );
            obList.add(ordersEntity);
        }

        return obList;
    }

    @Override
    public OrdersEntity search(String s, Connection connection) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean update(OrdersEntity ordersEntity, Connection connection) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String s, Connection connection) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean ifOrderExist(String oid, Connection connection) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String generateNewOrderId(Connection connection) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.executeQuery(connection, "SELECT oid FROM orders ORDER BY oid DESC LIMIT 1");

        if (resultSet.next()) {
            return resultSet.getString(1);
        } else {
            return null;
        }

    }
}
