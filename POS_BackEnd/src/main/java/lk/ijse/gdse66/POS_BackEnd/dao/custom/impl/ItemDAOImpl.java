package lk.ijse.gdse66.POS_BackEnd.dao.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.gdse66.POS_BackEnd.dao.CrudUtil;
import lk.ijse.gdse66.POS_BackEnd.dao.custom.ItemDAO;
import lk.ijse.gdse66.POS_BackEnd.entity.ItemEntity;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ItemDAOImpl implements ItemDAO {

    @Override
    public boolean add(ItemEntity itemEntity, Connection connection) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate(connection, "INSERT INTO item VALUES(?,?,?,?)",itemEntity.getItemCode(),
                itemEntity.getDescription(),
                itemEntity.getQtyOnHand(),
                itemEntity.getUnitPrice());
    }

    @Override
    public ObservableList<ItemEntity> getAll(Connection connection) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.executeQuery(connection ,"SELECT * FROM item" );

        ObservableList<ItemEntity> obList = FXCollections.observableArrayList();

        while (resultSet.next()){
            ItemEntity itemEntity = new ItemEntity(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getInt(3),
                    resultSet.getDouble(4)
            );

            obList.add(itemEntity);
        }
        return obList;
    }

    @Override
    public ItemEntity search(String code, Connection connection) throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.executeQuery(connection , "SELECT * FROM Item WHERE code=?" , code);
        if (rst.next()){
            return new ItemEntity(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getInt(3),
                    rst.getDouble(4)
            );
        }else {
            return null;
        }
    }

    @Override
    public boolean update(ItemEntity itemEntity, Connection connection) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String s, Connection connection) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean updateQtyOnHand(Connection connection, String id, int qty) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate(connection , "UPDATE item SET qtyOnHand=(qtyOnHand - "+ qty +")WHERE code=?", id);
    }
}
