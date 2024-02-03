package lk.ijse.gdse66.POS_BackEnd.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailsEntity {

    private String oId;
    private String iCode;
    private int qty;
    private double price;
    private double total;
}
