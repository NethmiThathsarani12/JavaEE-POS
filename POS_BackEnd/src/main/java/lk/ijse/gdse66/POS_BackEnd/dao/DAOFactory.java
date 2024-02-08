package lk.ijse.gdse66.POS_BackEnd.dao;

import lk.ijse.gdse66.POS_BackEnd.dao.custom.impl.*;

public class DAOFactory {

    private static DAOFactory daoFactory;

    private DAOFactory() {

    }

    public static DAOFactory getDaoFactory() {
        if (daoFactory == null) {
            daoFactory = new DAOFactory();
        }
        return daoFactory;
    }

    public SuperDAO getDAO(DAOTypes types) {
        switch (types) {
            case CUSTOMER:
                return new CustomerDAOImpl();
            case CUSTOM:
                return new QueryDAOImpl();
            case ITEM:
               return new ItemDAOImpl();
            case ORDERS:
               return new OrderDAOImpl();

            case ORDERDETAILS:
          return new OrderDetailsDAOImpl();

            default:
                return null;
        }
    }


    public enum DAOTypes {
        CUSTOMER,CUSTOM, ITEM , ORDERS,ORDERDETAILS
    }
}
