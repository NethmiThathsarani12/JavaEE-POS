package lk.ijse.gdse66.POS_BackEnd.dao.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.gdse66.POS_BackEnd.dao.CrudUtil;
import lk.ijse.gdse66.POS_BackEnd.dao.custom.CustomerDAO;
import lk.ijse.gdse66.POS_BackEnd.entity.CustomerEntity;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerDAOImpl implements CustomerDAO {


    @Override
    public boolean add(CustomerEntity customerEntity, Connection connection) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate(connection, "INSERT INTO company.customer (id,name,address,contact) VALUES(?,?,?,?)", customerEntity.getId(),
                customerEntity.getName(), customerEntity.getAddress(), customerEntity.getContact());
    }

    @Override
    public ObservableList<CustomerEntity> getAll(Connection connection) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.executeQuery(connection, "SELECT * FROM customer");

        ObservableList<CustomerEntity> obList = FXCollections.observableArrayList();

        while (resultSet.next()) {
            CustomerEntity customerEntity = new CustomerEntity(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4)

            );

            obList.add(customerEntity);
        }

        return obList;
    }

    @Override
    public CustomerEntity search(String id, Connection connection) throws SQLException, ClassNotFoundException {

        ResultSet rst = CrudUtil.executeQuery(connection, "SELECT * FROM customer WHERE id=?", id);
        if (rst.next()) {
            return new CustomerEntity(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4)
            );
        } else {
            return null;

        }


    }

    @Override
    public boolean update(CustomerEntity customerEntity, Connection connection) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate(connection, "UPDATE customer SET name=?,address=?,contact=? WHERE id=?", customerEntity.getName(),
                customerEntity.getAddress(), customerEntity.getContact(), customerEntity.getId());
    }

    @Override
    public boolean delete(String id, Connection connection) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate(connection, "DELETE FROM customer WHERE id=?", id);
    }
}


