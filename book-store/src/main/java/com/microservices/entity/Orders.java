package com.microservices.entity;

import com.microservices.vo.OrdersVO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;
@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long bookId;
    private Long bookUserId;
    private Date date;
    private Double price;
    private String paymentMethod;

    public Orders convert(OrdersVO vo){
        this.id = vo.getId();
        this.bookUserId = vo.getBookUserId();
        this.bookId = vo.getBookId();
        this.date = vo.getDate();
        this.price = vo.getPrice();
        this.paymentMethod = vo.getPaymentMethod();

        return this;
    }
}
