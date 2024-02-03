package lk.ijse.gdse66.POS_BackEnd.dao.custom;

import lk.ijse.gdse66.POS_BackEnd.dao.CrudDAO;
import lk.ijse.gdse66.POS_BackEnd.entity.OrderDetailsEntity;

import java.sql.Connection;

public interface OrderDetailsDAO extends CrudDAO<OrderDetailsEntity,String, Connection> {
}
