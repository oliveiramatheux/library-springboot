package com.microservices.controller;

import com.microservices.entity.BookUser;
import com.microservices.repository.BookUserRepository;
import com.microservices.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class Controller {

    @Autowired
    private BookUserRepository bookUserRepository;

    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public ResponseEntity<?> save(@RequestBody UserVO user){
        BookUser u = new BookUser();
        return ResponseEntity.ok(bookUserRepository.save(u.convert(user)));
    }

    @RequestMapping(value = "/users/update", method = RequestMethod.POST)
    public ResponseEntity<?> update(@RequestBody UserVO user){
        if(user.getId() == null){
            return ResponseEntity.badRequest().body("You must provide an ID");
        }
        BookUser u = new BookUser();
        return ResponseEntity.ok(bookUserRepository.save(u.convert(user)));
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public ResponseEntity<?> getUsers(){
        return ResponseEntity.ok(bookUserRepository.findAll());
    }

    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getUsersById(@PathVariable String id){
        Integer v = Integer.parseInt(id);
        if(v != null){
            return ResponseEntity.ok(bookUserRepository.findById(v.longValue()));
        }
        return ResponseEntity.badRequest().body("ID not valid");
    }

    @RequestMapping(value = "/users/email/{email}", method = RequestMethod.GET)
    public ResponseEntity<?> getUsersByEmail(@PathVariable String email){
        return ResponseEntity.ok(bookUserRepository.findByEmail(email).stream().findFirst());
    }

    @RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteUserById(@PathVariable String id){
        Integer v = Integer.parseInt(id);
        if(v != null){
             bookUserRepository.deleteById(v.longValue());
             return ResponseEntity.ok("User deleted");
        }
        return ResponseEntity.badRequest().body("ID not valid");
    }





}
