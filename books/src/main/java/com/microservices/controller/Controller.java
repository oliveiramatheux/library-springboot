package com.microservices.controller;

import com.microservices.entity.Books;
import com.microservices.repository.BooksRepository;
import com.microservices.vo.BooksVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class Controller {

    @Autowired
    private BooksRepository booksRepository;

    @RequestMapping(value = "/books", method = RequestMethod.POST)
    public ResponseEntity<?> save(@RequestBody BooksVO book) {
        Books b = new Books();
        return ResponseEntity.ok(booksRepository.save(b.convert(book)));
    }

    @RequestMapping(value = "/books/update", method = RequestMethod.POST)
    public ResponseEntity<?> update(@RequestBody BooksVO book) {
        if(book.getId() == null) {
            return ResponseEntity.badRequest().body("You must provide an ID");
        }
        Books b = new Books();
        return ResponseEntity.ok(booksRepository.save(b.convert(book)));
    }

    @RequestMapping(value = "/books", method = RequestMethod.GET)
    public ResponseEntity<?> getBooks() {
        return ResponseEntity.ok(booksRepository.findAll());
    }

    @RequestMapping(value = "/books/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getBookById(@PathVariable String id) {

        if(id == null) return ResponseEntity.badRequest().body("ID not valid");
        Integer bookId = Integer.parseInt(id);
        return ResponseEntity.ok(booksRepository.findById(bookId.longValue()));
    }

    @RequestMapping(value = "/books/name/{title}", method = RequestMethod.GET)
    public ResponseEntity<?> getBookByName(@PathVariable String title) {
        return ResponseEntity.ok(booksRepository.findByTitle(title));
    }

    @RequestMapping(value = "/books/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteByBookId(@PathVariable String id) {

        if(id == null) return ResponseEntity.badRequest().body("ID is not valid");
        
        Integer bookId = Integer.parseInt(id);
        booksRepository.deleteById(bookId.longValue());
        return ResponseEntity.ok("Book deleted");  
    }
}
