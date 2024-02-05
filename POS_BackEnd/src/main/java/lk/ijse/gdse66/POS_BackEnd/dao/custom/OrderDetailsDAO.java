package lk.ijse.gdse66.POS_BackEnd.dao.custom;

import lk.ijse.gdse66.POS_BackEnd.dao.CrudDAO;
import lk.ijse.gdse66.POS_BackEnd.entity.OrderDetailsEntity;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public interface OrderDetailsDAO extends CrudDAO<OrderDetailsEntity,String, Connection> {

    ArrayList<OrderDetailsEntity> searchOrderDetail(String id, Connection connection) throws SQLException,ClassNotFoundException;
}
