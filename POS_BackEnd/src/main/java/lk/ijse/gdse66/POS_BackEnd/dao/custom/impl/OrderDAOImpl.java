package lk.ijse.gdse66.POS_BackEnd.dao.custom.impl;

import javafx.collections.ObservableList;
import lk.ijse.gdse66.POS_BackEnd.dao.custom.OrderDAO;
import lk.ijse.gdse66.POS_BackEnd.entity.OrdersEntity;

import java.sql.Connection;
import java.sql.SQLException;

public class OrderDAOImpl implements OrderDAO {
    @Override
    public boolean add(OrdersEntity ordersEntity, Connection connection) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public ObservableList<OrdersEntity> getAll(Connection connection) throws SQLException, ClassNotFoundException {
        return null;
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
}
