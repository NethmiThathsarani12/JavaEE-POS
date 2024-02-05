package lk.ijse.gdse66.POS_BackEnd.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrdersDTO {

    private String oid;
    private String customerID;
    private Date date;
    private double total;
    private double discount;
    private double subTotal;
    private ArrayList<OrderDetailsDTO> orderDetail;

    public OrdersDTO(String oid, String customerId, Date date, double total, double discount, double subTotal) {

    }


}
