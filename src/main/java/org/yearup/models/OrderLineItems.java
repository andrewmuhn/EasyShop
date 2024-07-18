package org.yearup.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderLineItems {
    private int orderLineItemId;
    private int orderId;
    private int productId;
    private int quantity;
    private BigDecimal price;

    // Getters and setters
}

