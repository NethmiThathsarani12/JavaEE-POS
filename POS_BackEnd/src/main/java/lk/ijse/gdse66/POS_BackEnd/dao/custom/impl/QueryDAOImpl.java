package lk.ijse.gdse66.POS_BackEnd.dao.custom.impl;

import lk.ijse.gdse66.POS_BackEnd.dao.CrudUtil;
import lk.ijse.gdse66.POS_BackEnd.dao.custom.QueryDAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class QueryDAOImpl implements QueryDAO {
    @Override
    public int getSumOrders(Connection connection) throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.executeQuery(connection, "SELECT COUNT(oid) FROM `orders`");
        if (result.next()){
            return result.getInt(1);
        }else {
            return 0;
        }
    }

    @Override
    public int getItem(Connection connection) throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.executeQuery(connection,"SELECT COUNT(code) FROM item" );
        if (result.next()){
            return result.getInt(1);
        }else {
            return 0;
        }
    }

    @Override
    public int getCustomer(Connection connection) throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.executeQuery(connection, "SELECT COUNT(id) FROM customer");
        if (result.next()){
            return result.getInt(1);
        }else {
            return 0;
        }
    }
}
