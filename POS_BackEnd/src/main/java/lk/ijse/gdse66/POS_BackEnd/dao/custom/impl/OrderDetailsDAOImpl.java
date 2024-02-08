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
    public ArrayList<OrderDetailsEntity> getAll(Connection connection) throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.executeQuery(connection, "SELECT * FROM `orderdetails`");

        ArrayList<OrderDetailsEntity> orderDetailDTO = new ArrayList<>();
        while (result.next()) {
            orderDetailDTO.add(new OrderDetailsEntity(
                    result.getString(1),
                    result.getString(2),
                    result.getInt(3),
                    result.getDouble(4)

            ));

        }
        return orderDetailDTO;
    }

    @Override
    public boolean save(OrderDetailsEntity dto, Connection connection) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate(connection,"INSERT INTO orderdetails VALUES(?,?,?,?)" , dto.getOid(),
                dto.getItemCode(),dto.getQty(), dto.getTotal());

    }

    @Override
    public boolean update(OrderDetailsEntity dto, Connection connection) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public ArrayList<OrderDetailsEntity> searchId(String id, Connection connection) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean delete(String s, Connection connection) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String generateNewID(Connection connection) throws SQLException, ClassNotFoundException {
        return null;
    }
}
