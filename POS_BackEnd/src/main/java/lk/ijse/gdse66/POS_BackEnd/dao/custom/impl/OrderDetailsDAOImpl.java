package lk.ijse.gdse66.POS_BackEnd.dao.custom.impl;

import javafx.collections.ObservableList;
import lk.ijse.gdse66.POS_BackEnd.dao.custom.OrderDetailsDAO;
import lk.ijse.gdse66.POS_BackEnd.entity.OrderDetailsEntity;

import java.sql.Connection;
import java.sql.SQLException;

public class OrderDetailsDAOImpl implements OrderDetailsDAO {
    @Override
    public boolean add(OrderDetailsEntity orderDetailsEntity, Connection connection) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public ObservableList<OrderDetailsEntity> getAll(Connection connection) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public OrderDetailsEntity search(String s, Connection connection) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean update(OrderDetailsEntity orderDetailsEntity, Connection connection) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String s, Connection connection) throws SQLException, ClassNotFoundException {
        return false;
    }
}
