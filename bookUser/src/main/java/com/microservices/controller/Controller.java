package com.microservices.controller;

import com.microservices.entity.BookUser;
import com.microservices.repository.BookUserRepository;
import com.microservices.vo.UserVO;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class Controller {

    @Autowired
    private BookUserRepository bookUserRepository;

    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public ResponseEntity<?> save(@RequestBody UserVO user) {
        if(user.getEmail() == null) return ResponseEntity.badRequest().body("You must provide an email");

        Optional<BookUser> userExist = bookUserRepository.findByEmail(user.getEmail()).stream().findFirst();

        if(userExist.equals(Optional.empty()) == false) return ResponseEntity.badRequest().body("User already exist!");

        BookUser bookUser = new BookUser();
        return ResponseEntity.ok(bookUserRepository.save(bookUser.convert(user)));
    }

    @RequestMapping(value = "/users", method = RequestMethod.PATCH)
    public ResponseEntity<?> update(@RequestBody UserVO user) {
        if(user.getId() == null) return ResponseEntity.badRequest().body("You must provide an ID");

        BookUser bookUser = new BookUser();
        return ResponseEntity.ok(bookUserRepository.save(bookUser.convert(user)));
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public ResponseEntity<?> getUsers() {
        return ResponseEntity.ok(bookUserRepository.findAll());
    }

    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getUsersById(@PathVariable String id) {

        if(id == null) return ResponseEntity.badRequest().body("ID not valid");

        Integer userId = Integer.parseInt(id);
        return ResponseEntity.ok(bookUserRepository.findById(userId.longValue()));
    }

    @RequestMapping(value = "/users/email/{email}", method = RequestMethod.GET)
    public ResponseEntity<?> getUsersByEmail(@PathVariable String email) {
        return ResponseEntity.ok(bookUserRepository.findByEmail(email).stream().findFirst());
    }

    @RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteUserById(@PathVariable String id) {

        if(id == null) return ResponseEntity.badRequest().body("ID not valid");

        Integer userId = Integer.parseInt(id);
        bookUserRepository.deleteById(userId.longValue());
        return ResponseEntity.ok("User deleted");
    }
}
