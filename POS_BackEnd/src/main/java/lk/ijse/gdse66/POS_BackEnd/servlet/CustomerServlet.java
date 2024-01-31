package lk.ijse.gdse66.POS_BackEnd.servlet;

import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObjectBuilder;
import javafx.collections.ObservableList;
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

        try {

            String option = req.getParameter("option");
            String customerID = req.getParameter("cusId");
            resp.setContentType("application/json");
            Connection connection = dataSource.getConnection();
            PrintWriter writer = resp.getWriter();

            resp.addHeader("Access-Control-Allow-Origin", "*");

            switch (option) {
                case "SEARCH":

                    CustomerDTO customer = customerBO.searchCustomer(customerID, connection);
                    JsonObjectBuilder objectBuilder = Json.createObjectBuilder();

                    objectBuilder.add("id", customer.getCusId());
                    objectBuilder.add("name", customer.getCusName());
                    objectBuilder.add("address", customer.getCusAddress());
                    objectBuilder.add("contact", customer.getCusContact());

                    writer.print(objectBuilder.build());

                    break;

                case "GETALL":

                    ObservableList<CustomerDTO> allCustomers = customerBO.getAllCustomer(connection);
                    JsonArrayBuilder arrayBuilder1 = Json.createArrayBuilder();

                    for (CustomerDTO cust : allCustomers) {

                        JsonObjectBuilder objectBuilder1 = Json.createObjectBuilder();
                        objectBuilder1.add("id", cust.getCusId());
                        objectBuilder1.add("name", cust.getCusName());
                        objectBuilder1.add("address", cust.getCusAddress());
                        objectBuilder1.add("contact", cust.getCusContact());
                        arrayBuilder1.add(objectBuilder1.build());

                    }

                    JsonObjectBuilder response1 = Json.createObjectBuilder();
                    response1.add("status", 200);
                    response1.add("message", "Done");
                    response1.add("data", arrayBuilder1.build());
                    writer.print(response1.build());

                    break;
            }

            connection.close();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }



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
        resp.addHeader("Access-Control-Allow-Methods", "DELETE, PUT,GET,POST");
        resp.addHeader("Access-Control-Allow-Headers", "Content-Type");
    }

    public void destroy() {
    }
}