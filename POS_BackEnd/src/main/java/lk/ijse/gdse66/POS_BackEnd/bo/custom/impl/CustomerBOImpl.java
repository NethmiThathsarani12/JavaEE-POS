package lk.ijse.gdse66.POS_BackEnd.bo.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.gdse66.POS_BackEnd.bo.custom.CustomerBO;
import lk.ijse.gdse66.POS_BackEnd.dao.DAOFactory;
import lk.ijse.gdse66.POS_BackEnd.dao.custom.CustomerDAO;
import lk.ijse.gdse66.POS_BackEnd.dto.CustomerDTO;
import lk.ijse.gdse66.POS_BackEnd.entity.CustomerEntity;

import java.sql.Connection;
import java.sql.SQLException;

public class CustomerBOImpl implements CustomerBO {

    private final CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);

    @Override
    public boolean addCustomer(Connection connection, CustomerDTO customerDTO) throws SQLException, ClassNotFoundException {
        CustomerEntity customerEntity = new CustomerEntity(
        customerDTO.getCusId(), customerDTO.getCusName(), customerDTO.getCusAddress(), customerDTO.getCusContact()
        );

        return customerDAO.add(customerEntity,connection);
    }

    @Override
    public ObservableList<CustomerDTO> getAllCustomer(Connection connection) throws SQLException, ClassNotFoundException {
       ObservableList<CustomerEntity> customerEntities = customerDAO.getAll(connection);

       ObservableList<CustomerDTO> obList =  FXCollections.observableArrayList();

       for (CustomerEntity temp : customerEntities){
           CustomerDTO customerDTO = new CustomerDTO(
                   temp.getId(),
                   temp.getName(),
                   temp.getAddress(),
                   temp.getContact()
           );

           obList.add(customerDTO);
       }

       return obList;
    }

    @Override
    public CustomerDTO searchCustomer(String id, Connection connection) throws SQLException, ClassNotFoundException {
       CustomerEntity customerEntity = customerDAO.search(id,connection);

       CustomerDTO customerDTO = new CustomerDTO(
               customerEntity.getId(),
               customerEntity.getName(),
               customerEntity.getAddress(),
               customerEntity.getContact()
       );
       return customerDTO;

    }

    @Override
    public boolean updateCustomer(Connection connection, CustomerDTO customerDTO) throws SQLException, ClassNotFoundException {
       CustomerEntity customerEntity = new CustomerEntity(
               customerDTO.getCusId(),
               customerDTO.getCusName(),
               customerDTO.getCusAddress(),
               customerDTO.getCusContact()
       );
       return customerDAO.update(customerEntity,connection);
    }


}
