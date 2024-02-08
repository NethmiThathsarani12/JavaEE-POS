package lk.ijse.gdse66.POS_BackEnd.dao.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.gdse66.POS_BackEnd.dao.CrudUtil;
import lk.ijse.gdse66.POS_BackEnd.dao.custom.OrderDAO;
import lk.ijse.gdse66.POS_BackEnd.entity.OrdersEntity;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDAOImpl implements OrderDAO  {


    @Override
    public ArrayList<OrdersEntity> getAll(Connection connection) throws SQLException, ClassNotFoundException {


        ResultSet result = CrudUtil.executeQuery(connection,"SELECT * FROM orders" );

        ArrayList<OrdersEntity> obList = new ArrayList<>();
        while (result.next()){
            obList.add(new OrdersEntity(
                    result.getString(1),
                    result.getDate(2),
                    result.getString(3)
            ));
        }
        return obList;
    }

    @Override
    public boolean save(OrdersEntity dto, Connection connection) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate(connection , "INSERT INTO orders VALUES(?,?,?)" , dto.getOid(), dto.getDate(),dto.getCustomerID());
    }

    @Override
    public boolean update(OrdersEntity dto, Connection connection) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public ArrayList<OrdersEntity> searchId(String id, Connection connection) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean delete(String s, Connection connection) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String generateNewID(Connection connection) throws SQLException, ClassNotFoundException {

        ResultSet result = CrudUtil.executeQuery(connection ,"SELECT oid FROM orders ORDER BY oid DESC LIMIT 1" );
        if (result.next()){
            return result.getString(1);
        }else {
            return null;
        }
    }

    @Override
    public boolean mangeItems(int qty, String code, Connection connection) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate(connection,"UPDATE item SET qtyOnHand=qtyOnHand-? WHERE code=?" , qty, code);
    }
}
