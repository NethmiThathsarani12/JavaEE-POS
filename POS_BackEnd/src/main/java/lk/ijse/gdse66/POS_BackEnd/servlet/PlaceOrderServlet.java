package lk.ijse.gdse66.POS_BackEnd.servlet;

import jakarta.json.*;
import javafx.collections.ObservableList;
import lk.ijse.gdse66.POS_BackEnd.bo.BOFactory;
import lk.ijse.gdse66.POS_BackEnd.bo.custom.OrderBO;
import lk.ijse.gdse66.POS_BackEnd.dto.OrderDetailsDTO;
import lk.ijse.gdse66.POS_BackEnd.dto.OrdersDTO;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "orderServlet" , urlPatterns = "/orders")
public class PlaceOrderServlet extends HttpServlet {

    @Resource(name = "java:comp/env/jdbc/pool")
    DataSource dataSource;

    private final OrderBO orderBO = (OrderBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ORDERS);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        try {

            String option = req.getParameter("option");
            String orderID = req.getParameter("orderId");
            resp.setContentType("application/json");
            Connection connection = dataSource.getConnection();
            PrintWriter writer = resp.getWriter();

            resp.addHeader("Access-Control-Allow-Origin", "*");

            switch (option) {

                case "SEARCH":

                    ArrayList<OrderDetailsDTO> orderDetails = orderBO.searchOrderDetails(orderID, connection);

                    JsonArrayBuilder arrayBuilder1 = Json.createArrayBuilder();

                    for (OrderDetailsDTO orderDetail : orderDetails) {
                        JsonObjectBuilder ob = Json.createObjectBuilder();
                        ob.add("orderId", orderDetail.getOid());
                        ob.add("iCode", orderDetail.getItemCode());
                        ob.add("qty", orderDetail.getQty());
                        ob.add("price", orderDetail.getUnitPrice());
                        ob.add("total", orderDetail.getTotal());

                        arrayBuilder1.add(ob.build());
                    }
                    writer.write(String.valueOf(arrayBuilder1.build()));

                    break;

                case "GETID":

                    JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder();
                    jsonObjectBuilder.add("orderId", orderBO.generateNewOrderId(connection));
                    writer.print(jsonObjectBuilder.build());

                    break;

                case "GETALL":

                    ObservableList<OrdersDTO> allOrders = orderBO.getAllOrders(connection);
                    JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();

                    for (OrdersDTO ordersDTO : allOrders) {

                        JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
                        objectBuilder.add("orderId", ordersDTO.getOid());
                        objectBuilder.add("orderDate", String.valueOf(ordersDTO.getDate()));
                        objectBuilder.add("cid", ordersDTO.getCustomerID());
                        objectBuilder.add("total", ordersDTO.getTotal());
                        objectBuilder.add("discount", ordersDTO.getDiscount());
                        objectBuilder.add("subTotal", ordersDTO.getSubTotal());
                        arrayBuilder.add(objectBuilder.build());

                    }

                    JsonObjectBuilder response = Json.createObjectBuilder();
                    response.add("status", 200);
                    response.add("message", "Done");
                    response.add("data", arrayBuilder.build());
                    writer.print(response.build());

                    break;
            }

            connection.close();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            resp.setContentType("application/json");
            Connection connection = dataSource.getConnection();
            PrintWriter writer = resp.getWriter();

            resp.addHeader("Access-Control-Allow-Origin", "*");

            JsonReader reader = Json.createReader(req.getReader());
            JsonObject jsonObject = reader.readObject();
            JsonArray oDetail = jsonObject.getJsonArray("ODetail");

            ArrayList<OrderDetailsDTO> orderDetailsDTOS = new ArrayList<>();
            for (JsonValue orderDetail: oDetail) {
                JsonObject asJsonObject = orderDetail.asJsonObject();
                orderDetailsDTOS.add(new OrderDetailsDTO(
                        asJsonObject.getString("orderId"),
                        asJsonObject.getString("itemCode"),
                        Integer.parseInt(asJsonObject.getString("qty")),
                        Double.parseDouble(asJsonObject.getString("price")),
                        Double.parseDouble(asJsonObject.getString("total"))
                ));

            }

            OrdersDTO ordersDTO = new OrdersDTO(
                    jsonObject.getString("orderID"),
                    Date.valueOf(jsonObject.getString("orderDate")),
                    jsonObject.getString("cId"),
                    Double.parseDouble(jsonObject.getString("total")),
                    Double.parseDouble(jsonObject.getString("subTotal")),
                    Double.parseDouble(jsonObject.getString("discount")),
                    orderDetailsDTOS
            );

            if (orderBO.saveOrder(connection, ordersDTO)){
                resp.setStatus(HttpServletResponse.SC_OK);
                JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
                objectBuilder.add("status",resp.getStatus());
                objectBuilder.add("message","Successfully Added");
                objectBuilder.add("data","");

                writer.print(objectBuilder.build());
            }

            connection.close();

        } catch (SQLException | ClassNotFoundException throwables) {

            resp.setStatus(HttpServletResponse.SC_OK);
            JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
            objectBuilder.add("status", resp.getStatus());
            objectBuilder.add("message", "Error");
            objectBuilder.add("data", throwables.getLocalizedMessage());
            throwables.printStackTrace();

            throwables.printStackTrace();
        }
    }

    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.addHeader("Access-Control-Allow-Origin", "*");
        resp.addHeader("Access-Control-Allow-Methods", "DELETE, PUT");
        resp.addHeader("Access-Control-Allow-Headers", "Content-Type");
    }

    }

