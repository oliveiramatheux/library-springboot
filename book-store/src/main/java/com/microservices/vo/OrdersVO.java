package com.microservices.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrdersVO {
    private Long id;
    private Long bookId;
    private Long bookUserId;
    private Date date;
    private Double price;
    private String paymentMethod;
}
