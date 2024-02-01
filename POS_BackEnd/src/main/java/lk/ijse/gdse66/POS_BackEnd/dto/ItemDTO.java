package lk.ijse.gdse66.POS_BackEnd.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemDTO {

    private String itemCode;
    private String description;
    private int  qtyOnHand;
    private double unitPrice;

}
