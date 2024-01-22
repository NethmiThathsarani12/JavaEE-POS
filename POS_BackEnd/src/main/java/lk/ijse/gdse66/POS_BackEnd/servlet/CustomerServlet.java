package lk.ijse.gdse66.POS_BackEnd.servlet;

import lk.ijse.gdse66.POS_BackEnd.bo.BOFactory;
import lk.ijse.gdse66.POS_BackEnd.bo.custom.CustomerBO;
import lk.ijse.gdse66.POS_BackEnd.dto.CustomerDTO;
import sun.jvm.hotspot.debugger.DataSource;

import java.io.*;
import java.sql.Connection;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "customerServlet", urlPatterns = "/customer")
public class CustomerServlet extends HttpServlet {
    private String message;


    DataSource dataSource;

    private final CustomerBO customerBO = (CustomerBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.CUSTOMER);


    public void init() {
        message = "Hello World!";
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        PrintWriter writer = resp.getWriter();
        resp.setContentType("application/json");

        Connection connection = null;

        resp.addHeader("Access-Control-Allow-Origin", "*");

//        try {
//            connection = dataSource.getConnection();
//
//            CustomerDTO customerDTO = new CustomerDTO(
//                    req.getParameter("cusId"),
//                    req.getParameter("cusName"),
//                    req.getParameter("cusAddress"),
//                    req.getParameter("cusTp")
//            );
//
//            try {
//                if (customerBO.addCustomer(connection, customerDTO)){
//
//                }
//            }
//    }

}

    public void destroy() {
    }
}