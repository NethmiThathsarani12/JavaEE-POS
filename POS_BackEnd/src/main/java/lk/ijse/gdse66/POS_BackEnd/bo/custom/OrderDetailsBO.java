package lk.ijse.gdse66.POS_BackEnd.bo.custom;

import lk.ijse.gdse66.POS_BackEnd.bo.SuperBO;
import lk.ijse.gdse66.POS_BackEnd.dto.OrderDetailsDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public interface OrderDetailsBO extends SuperBO {
    ArrayList<OrderDetailsDTO> getAllOrderDetails(Connection connection) throws SQLException, ClassNotFoundException;

    boolean purchaseOrderDetails(OrderDetailsDTO dto, Connection connection) throws SQLException, ClassNotFoundException;
}
