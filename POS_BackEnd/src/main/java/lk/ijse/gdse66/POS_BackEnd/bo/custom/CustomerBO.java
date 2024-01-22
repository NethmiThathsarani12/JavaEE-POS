package lk.ijse.gdse66.POS_BackEnd.bo.custom;

import lk.ijse.gdse66.POS_BackEnd.bo.SuperBO;
import lk.ijse.gdse66.POS_BackEnd.dto.CustomerDTO;

import java.sql.Connection;
import java.sql.SQLException;

public interface CustomerBO extends SuperBO {

    boolean addCustomer(Connection connection, CustomerDTO customerDTO) throws SQLException, ClassNotFoundException;
}
