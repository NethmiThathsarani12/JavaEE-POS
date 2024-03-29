package lk.ijse.gdse66.POS_BackEnd.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    private String oid;
    private Date date;
    private String customerID;
    private double total;
    private double subTotal;
    private double discount;




}
