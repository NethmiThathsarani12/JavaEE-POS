package lk.ijse.gdse66.POS_BackEnd.dao.custom.impl;

import lk.ijse.gdse66.POS_BackEnd.dao.CrudUtil;
import lk.ijse.gdse66.POS_BackEnd.dao.custom.CustomerDAO;
import lk.ijse.gdse66.POS_BackEnd.entity.CustomerEntity;

import java.sql.Connection;
import java.sql.SQLException;

public class CustomerDAOImpl implements CustomerDAO {


    @Override
    public boolean add(CustomerEntity customerEntity, Connection connection) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate(connection, "INSERT INTO company.customer (id,name,address,contact) VALUES(?,?,?,?)" , customerEntity.getId(),
               customerEntity.getName(),customerEntity.getAddress(),customerEntity.getContact());
    }

}
