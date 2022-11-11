package com.microservices.controller;

import com.microservices.entity.Orders;
import com.microservices.repository.OrdersRepository;
import com.microservices.vo.OrdersVO;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController

public class Controller {

    @Autowired
    private OrdersRepository ordersRepository;

    @RequestMapping(value = "/orders", method = RequestMethod.POST)
    public ResponseEntity<?> sellBook(@RequestBody OrdersVO oVO){
        Orders o = new Orders();
        return ResponseEntity.ok(ordersRepository.save(o.convert(oVO)));
    }

    @RequestMapping(value = "/orders/update", method = RequestMethod.POST)
    public ResponseEntity<?> updateOrder(@RequestBody OrdersVO oVO){
        if(oVO.getId() == null){
            return ResponseEntity.badRequest().body("You must provide an ID");
        }
        Orders o = new Orders();
        return ResponseEntity.ok(ordersRepository.save(o.convert(oVO)));
    }

    @RequestMapping(value = "/orders", method = RequestMethod.GET)
    public ResponseEntity<?> getAllOrders(){
        return ResponseEntity.ok(ordersRepository.findAll());
    }

    @RequestMapping(value = "/orders/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteOrder(@RequestParam String id){
        Integer intId = Integer.parseInt(id);
        if(intId != null){
            ordersRepository.deleteById(intId.longValue());
            return ResponseEntity.ok("Order deleted");
        }

        return ResponseEntity.badRequest().body("You must provide an ID");
    }

    @RequestMapping(value = "/orders/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getOrderById(@PathVariable String id){
        Integer intId = Integer.parseInt(id);
        if(intId != null){
            ordersRepository.deleteById(intId.longValue());
            return ResponseEntity.ok(ordersRepository.findById(intId.longValue()));
        }

        return ResponseEntity.badRequest().body("You must provide an ID");
    }

    @RequestMapping(value = "/orders/report/sellsAmount24hours", method = RequestMethod.GET)
    public ResponseEntity<?> getReport24Hours() throws JSONException {
        Collection<Double> d = ordersRepository.getSellsAmount24hours();
        JSONObject resp = new JSONObject();
        resp.put("value", d.toArray()[0]);
        return ResponseEntity.ok(resp);
    }

    @RequestMapping(value = "/orders/report/sells24hours", method = RequestMethod.GET)
    public ResponseEntity<?> getSellsReport24Hours(){
        return ResponseEntity.ok(ordersRepository.getSells24hours());
    }
}
