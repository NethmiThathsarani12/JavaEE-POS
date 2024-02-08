package lk.ijse.gdse66.POS_BackEnd.bo.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.gdse66.POS_BackEnd.bo.custom.OrderBO;
import lk.ijse.gdse66.POS_BackEnd.dao.DAOFactory;
import lk.ijse.gdse66.POS_BackEnd.dao.custom.CustomerDAO;
import lk.ijse.gdse66.POS_BackEnd.dao.custom.ItemDAO;
import lk.ijse.gdse66.POS_BackEnd.dao.custom.OrderDAO;
import lk.ijse.gdse66.POS_BackEnd.dao.custom.OrderDetailsDAO;
import lk.ijse.gdse66.POS_BackEnd.dto.CustomerDTO;
import lk.ijse.gdse66.POS_BackEnd.dto.ItemDTO;
import lk.ijse.gdse66.POS_BackEnd.dto.OrderDetailsDTO;
import lk.ijse.gdse66.POS_BackEnd.dto.OrdersDTO;
import lk.ijse.gdse66.POS_BackEnd.entity.OrderDetailsEntity;
import lk.ijse.gdse66.POS_BackEnd.entity.OrdersEntity;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderBOImpl implements OrderBO {

    private final OrderDAO orderDAO = (OrderDAO)  DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDERS);

    @Override
    public boolean purchaseOrder(OrdersDTO dto, Connection connection) throws SQLException, ClassNotFoundException {
        return orderDAO.save(new OrdersEntity(dto.getOid(), dto.getDate(), dto.getCustomerID()), connection);
    }

    @Override
    public ArrayList<OrdersDTO> getAllOrders(Connection connection) throws SQLException, ClassNotFoundException {
        ArrayList<OrdersEntity> all = orderDAO.getAll(
                connection
        );
        ArrayList<OrdersDTO> arrayList = new ArrayList<>();
        for (OrdersEntity ordersEntity : all){
            arrayList.add(new OrdersDTO(
                    ordersEntity.getOid(),
                    ordersEntity.getDate(),
                    ordersEntity.getCustomerID()
            ));

        }
        return arrayList;
    }

    @Override
    public String generateNewOrder(Connection connection) throws SQLException, ClassNotFoundException {
        return orderDAO.generateNewID(connection);
    }

    @Override
    public boolean mangeItems(int qty, String code, Connection connection) throws SQLException, ClassNotFoundException {
        return orderDAO.mangeItems(qty, code, connection);
    }
}
