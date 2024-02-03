package lk.ijse.gdse66.POS_BackEnd.dao.custom;

import lk.ijse.gdse66.POS_BackEnd.dao.CrudDAO;
import lk.ijse.gdse66.POS_BackEnd.entity.OrdersEntity;

import java.sql.Connection;

public interface OrderDAO extends CrudDAO<OrdersEntity,String, Connection> {

}
