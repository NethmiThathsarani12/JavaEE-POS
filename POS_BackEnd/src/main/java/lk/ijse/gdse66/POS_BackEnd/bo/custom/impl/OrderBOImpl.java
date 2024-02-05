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

    @Resource(name = "java:comp/env/jdbc/pool")
    DataSource dataSource;

    private final CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);
    private final ItemDAO itemDAO = (ItemDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ITEM);
    private final OrderDAO orderDAO = (OrderDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDERS);
    private final OrderDetailsDAO orderDetailsDAO = (OrderDetailsDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDERDETAILS);

    @Override
    public boolean saveOrder(Connection connection, OrdersDTO ordersDTO) throws SQLException, ClassNotFoundException {

        Connection con = null;

        try {
            con = connection;

            connection.setAutoCommit(false);

            boolean orderAvailable = false;
            orderAvailable = orderDAO.ifOrderExist(ordersDTO.getOid(),connection);

            if (orderAvailable){
                return false;
            }

            OrdersEntity ordersEntity = new OrdersEntity(ordersDTO.getOid(),
                    ordersDTO.getCustomerID(),
                    ordersDTO.getDate(),
                    ordersDTO.getTotal(),
                    ordersDTO.getDiscount(),
                    ordersDTO.getSubTotal());

            boolean orderAdded = orderDAO.add(ordersEntity, connection);

            if (orderAdded){
                if (saveOrderDetail(connection , ordersDTO)){
                    connection.commit();
                    return true;
                }else {
                    connection.rollback();
                    return false;
                }
            }else {
                connection.rollback();
                return false;
            }
        }catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }finally {
            try {
                connection.setAutoCommit(true);
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        return true;
    }

    @Override
    public boolean saveOrderDetail(Connection connection, OrdersDTO ordersDTO) throws SQLException, ClassNotFoundException {


        for (OrderDetailsDTO item : ordersDTO.getOrderDetail()){

            OrderDetailsEntity orderDetailsEntity = new OrderDetailsEntity(
                    item.getOid(),
                    item.getItemCode(),
                    item.getQty(),
                    item.getUnitPrice(),
                    item.getTotal()
            );

            boolean ifOrderDetailSaved = orderDetailsDAO.add(orderDetailsEntity,
                    connection
            );
            if (ifOrderDetailSaved){
                return updateQtyOnHand(connection, item.getItemCode(),
                        item.getQty());
            }else {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean updateQtyOnHand(Connection connection, String id, int qty) throws SQLException, ClassNotFoundException {
        return itemDAO.updateQtyOnHand(connection,id, qty);
    }

    @Override
    public ObservableList<OrdersDTO> getAllOrders(Connection connection) throws SQLException, ClassNotFoundException {

        ObservableList<OrdersEntity> ordersEntities = orderDAO.getAll(connection);

        ObservableList<OrdersDTO> obList = FXCollections.observableArrayList();

        for (OrdersEntity temp : ordersEntities){
            OrdersDTO ordersDTO =  new OrdersDTO(
                    temp.getOid(),
                    temp.getCustomerID(),
                    temp.getDate(),
                    temp.getTotal(),
                    temp.getDiscount(),
                    temp.getSubTotal()
            );

            obList.add(ordersDTO);
        }

        return obList;


    }

    @Override
    public ObservableList<OrderDetailsDTO> getAllOrderDetails(Connection connection) throws SQLException, ClassNotFoundException {
        ObservableList<OrderDetailsEntity> orderDetailsEntities = orderDetailsDAO.getAll(connection);

        ObservableList<OrderDetailsDTO> obList = FXCollections.observableArrayList();

        for (OrderDetailsEntity temp : orderDetailsEntities){
            OrderDetailsDTO orderDetailsDTO =  new OrderDetailsDTO(
                    temp.getOid(),
                    temp.getItemCode(),
                    temp.getQty(),
                    temp.getUnitPrice(),
                    temp.getTotal()
            );

            obList.add(orderDetailsDTO);
        }
        return obList;
    }

    @Override
    public ArrayList<OrderDetailsDTO> searchOrderDetails(String orderId, Connection connection) throws SQLException, ClassNotFoundException {

        ArrayList<OrderDetailsEntity> orderDetailsEntities = orderDetailsDAO.searchOrderDetail(orderId,connection);
        ArrayList<OrderDetailsDTO> orderDetailsDTOS = new ArrayList<>();
        for (OrderDetailsEntity orderDetailsEntity : orderDetailsEntities){
            orderDetailsDTOS.add(new OrderDetailsDTO(
                    orderDetailsEntity.getOid(),
                    orderDetailsEntity.getItemCode(),
                    orderDetailsEntity.getQty(),
                    orderDetailsEntity.getUnitPrice(),
                    orderDetailsEntity.getTotal()

            ));

        }
        return orderDetailsDTOS;

    }

    @Override
    public String generateNewOrderId(Connection connection) throws SQLException, ClassNotFoundException {
        return orderDAO.generateNewOrderId(connection);
    }

    @Override
    public ArrayList<CustomerDTO> getAllCustomers(Connection connection) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ArrayList<ItemDTO> getAllItems(Connection connection) throws SQLException, ClassNotFoundException {
        return null;
    }
}
