package lk.ijse.gdse66.POS_BackEnd.bo.custom;

import javafx.collections.ObservableList;
import lk.ijse.gdse66.POS_BackEnd.bo.SuperBO;
import lk.ijse.gdse66.POS_BackEnd.dto.ItemDTO;

import java.sql.Connection;
import java.sql.SQLException;

public interface ItemBO extends SuperBO {

    boolean addItem (Connection connection, ItemDTO itemDTO) throws SQLException,ClassNotFoundException;

    ObservableList<ItemDTO> getAllItem (Connection connection) throws SQLException,ClassNotFoundException;

    ItemDTO searchItem(String itemCode, Connection connection) throws SQLException,ClassNotFoundException;
}
