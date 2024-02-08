package lk.ijse.gdse66.POS_BackEnd.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrdersDTO {

    private String oid;
    private Date date;
    private String customerID;

//    public OrdersDTO(String oid , String date, String customerID){
//
//    }

}
