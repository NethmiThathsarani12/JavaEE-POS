package lk.ijse.gdse66.POS_BackEnd.dao.custom;

import lk.ijse.gdse66.POS_BackEnd.dao.CrudDAO;
import lk.ijse.gdse66.POS_BackEnd.dao.DAO;
import lk.ijse.gdse66.POS_BackEnd.entity.OrdersEntity;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public interface OrderDAO extends DAO<OrdersEntity, String> {

    boolean mangeItems(int qty, String code, Connection connection) throws SQLException, ClassNotFoundException;

}