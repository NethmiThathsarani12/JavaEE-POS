package lk.ijse.gdse66.POS_BackEnd.dao.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.gdse66.POS_BackEnd.dao.CrudUtil;
import lk.ijse.gdse66.POS_BackEnd.dao.custom.OrderDetailsDAO;
import lk.ijse.gdse66.POS_BackEnd.entity.OrderDetailsEntity;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDetailsDAOImpl implements OrderDetailsDAO {
    @Override
    public boolean add(OrderDetailsEntity orderDetailsEntity, Connection connection) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate(connection, "INSERT INTO orderdetails VALUES (?,?,?,?,?)" , orderDetailsEntity.getOid(),
                orderDetailsEntity.getItemCode(),
                orderDetailsEntity.getQty(),
                orderDetailsEntity.getUnitPrice(),
                orderDetailsEntity.getTotal());
    }

    @Override
    public ObservableList<OrderDetailsEntity> getAll(Connection connection) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.executeQuery(connection,"SELECT * FROM orderdetails" );

        ObservableList<OrderDetailsEntity> obList = FXCollections.observableArrayList();

        while (resultSet.next()){
            OrderDetailsEntity orderDetailsEntity = new OrderDetailsEntity(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getInt(3),
                    resultSet.getDouble(4),
                    resultSet.getDouble(5)
            );

            obList.add(orderDetailsEntity);
        }
        return obList;
    }

    @Override
    public OrderDetailsEntity search(String s, Connection connection) throws SQLException, ClassNotFoundException {
       throw new UnsupportedOperationException("Not Supported Yet");
    }

    @Override
    public boolean update(OrderDetailsEntity orderDetailsEntity, Connection connection) throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("Not Supported Yet");
    }

    @Override
    public boolean delete(String s, Connection connection) throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("Not Supported Yet");
    }

    @Override
    public ArrayList<OrderDetailsEntity> searchOrderDetail(String oId, Connection connection) throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.executeQuery(connection, "SELECT * FROM orderdetails WHERE oid =?" , oId
        );

        ArrayList<OrderDetailsEntity> orderDetailsEntities = new ArrayList<>();

        while (rst.next()){
            orderDetailsEntities.add(new OrderDetailsEntity(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getInt(3),
                    rst.getDouble(4),
                    rst.getDouble(5)
            ));
        }
        return orderDetailsEntities;
    }
}
