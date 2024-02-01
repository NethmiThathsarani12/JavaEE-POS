package lk.ijse.gdse66.POS_BackEnd.bo.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.gdse66.POS_BackEnd.bo.custom.ItemBO;
import lk.ijse.gdse66.POS_BackEnd.dao.DAOFactory;
import lk.ijse.gdse66.POS_BackEnd.dao.custom.ItemDAO;
import lk.ijse.gdse66.POS_BackEnd.dto.ItemDTO;
import lk.ijse.gdse66.POS_BackEnd.entity.ItemEntity;

import java.sql.Connection;
import java.sql.SQLException;

public class ItemBOImpl implements ItemBO {

    private final ItemDAO itemDAO = (ItemDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ITEM);

    @Override
    public boolean addItem(Connection connection, ItemDTO itemDTO) throws SQLException, ClassNotFoundException {
        ItemEntity itemEntity =new  ItemEntity(
                itemDTO.getItemCode(),itemDTO.getDescription(),itemDTO.getQtyOnHand(),itemDTO.getUnitPrice()
        );

        return itemDAO.add(itemEntity,connection);
    }

    @Override
    public ObservableList<ItemDTO> getAllItem(Connection connection) throws SQLException, ClassNotFoundException {
       ObservableList<ItemEntity> itemEntities = itemDAO.getAll(connection);

       ObservableList<ItemDTO> obList = FXCollections.observableArrayList();

       for (ItemEntity temp: itemEntities){
           ItemDTO itemDTO = new ItemDTO(
                   temp.getItemCode(),
                   temp.getDescription(),
                   temp.getQtyOnHand(),
                   temp.getUnitPrice()
           );

           obList.add(itemDTO);
       }

       return obList;
    }

    @Override
    public ItemDTO searchItem(String itemCode, Connection connection) throws SQLException, ClassNotFoundException {
        ItemEntity itemEntity = itemDAO.search(itemCode, connection);

        ItemDTO itemDTO = new ItemDTO(
                itemEntity.getItemCode(),
                itemEntity.getDescription(),
                itemEntity.getQtyOnHand(),
                itemEntity.getUnitPrice()
        );
        return itemDTO;
    }

    @Override
    public boolean updateItem(Connection connection, ItemDTO itemDTO) throws SQLException, ClassNotFoundException {
        ItemEntity itemEntity = new ItemEntity(
                itemDTO.getItemCode(),
                itemDTO.getDescription(),
                itemDTO.getQtyOnHand(),
                itemDTO.getUnitPrice()
        );
        return itemDAO.update(itemEntity,connection);
    }
}
