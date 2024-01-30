package lk.ijse.gdse66.POS_BackEnd.servlet;

import jakarta.json.Json;
import jakarta.json.JsonObjectBuilder;
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


//    public void init() {
//        message = "Hello World!";
//    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        PrintWriter writer = resp.getWriter();
        resp.setContentType("application/json");

        Connection connection = null;

        resp.addHeader("Access-Control-Allow-Origin", "*");

        try {
            connection = dataSource.getConnection();

            CustomerDTO customerDTO = new CustomerDTO(
                    req.getParameter("txtCustomerID"),
                    req.getParameter("txtCustomerName"),
                    req.getParameter("txtCustomerAddress"),
                    req.getParameter("txtCustomerContact")
            );

            try {
                if (customerBO.addCustomer(connection, customerDTO)) {
                    JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
                    resp.setStatus(HttpServletResponse.SC_ACCEPTED);
                    objectBuilder.add("status", 200);
                    objectBuilder.add("message", "Successfully Added");
                    objectBuilder.add("data", "");
                    writer.print(objectBuilder.build());
                }
            } catch (ClassNotFoundException e) {
                JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
                objectBuilder.add("status",400);
                objectBuilder.add("message","Error");
                objectBuilder.add("data", e.getLocalizedMessage());
                writer.print(objectBuilder.build());
                resp.setStatus(HttpServletResponse.SC_OK);
                e.printStackTrace();
                e.printStackTrace();
            }

            connection.close();


        }catch (SQLException e){
            JsonObjectBuilder objectBuilder  = Json.createObjectBuilder();
            objectBuilder.add("status" , 400);
            objectBuilder.add("message", "Error");
            objectBuilder.add("data" , e.getLocalizedMessage());
            writer.print(objectBuilder.build());
            resp.setStatus(HttpServletResponse.SC_OK);
            e.printStackTrace();
        }

    }

    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.addHeader("Access-Control-Allow-Origin", "*");
        resp.addHeader("Access-Control-Allow-Methods", "DELETE, PUT");
        resp.addHeader("Access-Control-Allow-Headers", "Content-Type");
    }

    public void destroy() {
    }
}