package org.yearup.models;

import jdk.jfr.DataAmount;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Orders {
    private String orderId;
    private String userId;
    private String orderDate;
    private String address;
    private String city;
    private String state;
    private String zip;
    private BigDecimal shippingAmount;

}





