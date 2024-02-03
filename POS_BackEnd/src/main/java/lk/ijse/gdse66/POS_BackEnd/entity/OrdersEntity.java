package lk.ijse.gdse66.POS_BackEnd.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrdersEntity {

    private String oid;
    private String customerID;
    private Date date;
    private double total;
    private double discount;
    private double subTotal;
}
