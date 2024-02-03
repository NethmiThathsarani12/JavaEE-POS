package lk.ijse.gdse66.POS_BackEnd.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailsDTO {

    private String oId;
    private String iCode;
    private int oQty;
    private double price;
    private double total;
}
