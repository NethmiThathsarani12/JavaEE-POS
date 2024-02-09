package lk.ijse.gdse66.POS_BackEnd.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetails {

    private String oid;
    private String itemCode;
    private int qty;
    private double unitPrice;
    private double total;

}
