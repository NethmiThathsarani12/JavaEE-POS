package lk.ijse.gdse66.POS_BackEnd.bo.custom;

import javafx.collections.ObservableList;
import lk.ijse.gdse66.POS_BackEnd.bo.SuperBO;
import lk.ijse.gdse66.POS_BackEnd.dto.CustomerDTO;
import lk.ijse.gdse66.POS_BackEnd.dto.ItemDTO;
import lk.ijse.gdse66.POS_BackEnd.dto.OrderDetailsDTO;
import lk.ijse.gdse66.POS_BackEnd.dto.OrdersDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public interface OrderBO extends SuperBO {

    boolean purchaseOrder(OrdersDTO dto, Connection connection) throws SQLException, ClassNotFoundException;

    ArrayList<OrdersDTO> getAllOrders(Connection connection) throws SQLException, ClassNotFoundException;

    String generateNewOrder(Connection connection) throws SQLException, ClassNotFoundException;

    boolean mangeItems(int qty, String code, Connection connection) throws SQLException, ClassNotFoundException;



}
