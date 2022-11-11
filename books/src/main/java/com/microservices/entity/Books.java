package com.microservices.entity;

import com.microservices.vo.BooksVO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Books {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String author;
    private String publisher;
    private String publishedDate;
    private String description;
    private Integer pageCount;

    public Books convert(BooksVO vo){
        this.id = vo.getId();
        this.title = vo.getTitle();
        this.author = vo.getAuthor();
        this.publisher = vo.getPublisher();
        this.publishedDate = vo.getPublishedDate();
        this.description = vo.getDescription();
        this.pageCount = vo.getPageCount();
        return this;
    }
}
