package lk.ijse.gdse66.POS_BackEnd.servlet;

import lk.ijse.gdse66.POS_BackEnd.bo.BOFactory;
import lk.ijse.gdse66.POS_BackEnd.bo.custom.CustomerBO;
import lk.ijse.gdse66.POS_BackEnd.dto.CustomerDTO;

import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import javax.sql.DataSource;


@WebServlet(name = "customerServlet", urlPatterns = "/customer")
public class CustomerServlet extends HttpServlet {
    private String message;

    @Resource(name = "java:comp/env/jdbc/pool")
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

       try {
           connection = dataSource.getConnection();
       } catch (SQLException e) {
           e.printStackTrace();
       }


    }

    public void destroy() {
    }
}