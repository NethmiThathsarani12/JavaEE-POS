package lk.ijse.gdse66.POS_BackEnd.bo.custom.impl;

import lk.ijse.gdse66.POS_BackEnd.bo.custom.OrderDetailsBO;
import lk.ijse.gdse66.POS_BackEnd.dao.DAOFactory;
import lk.ijse.gdse66.POS_BackEnd.dao.custom.OrderDetailsDAO;
import lk.ijse.gdse66.POS_BackEnd.dto.OrderDetailsDTO;
import lk.ijse.gdse66.POS_BackEnd.entity.OrderDetailsEntity;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;


public class OrderDetailsBOImpl implements OrderDetailsBO {

    private final OrderDetailsDAO orderDetailsDAO = (OrderDetailsDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDERDETAILS);

    @Override
    public ArrayList<OrderDetailsDTO> getAllOrderDetails(Connection connection) throws SQLException, ClassNotFoundException {
        ArrayList<OrderDetailsEntity> all = orderDetailsDAO.getAll(connection);

        ArrayList<OrderDetailsDTO> allOrderDetails = new ArrayList<>();
        for (OrderDetailsEntity orderDetailsEntity : all){
            allOrderDetails.add(new OrderDetailsDTO(
                    orderDetailsEntity.getOid(),
                    orderDetailsEntity.getItemCode(),
                    orderDetailsEntity.getQty(),
                    orderDetailsEntity.getTotal()
            ));

        }
        return allOrderDetails;
    }

    @Override
    public boolean purchaseOrderDetails(OrderDetailsDTO dto, Connection connection) throws SQLException, ClassNotFoundException {
        return orderDetailsDAO.save(new OrderDetailsEntity(dto.getOid(),
                dto.getItemCode(),
                dto.getQty(),
                dto.getTotal()),connection);
    }
}
