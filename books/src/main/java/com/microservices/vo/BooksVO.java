package com.microservices.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BooksVO {
    private Long id;
    private String title;
    private String author;
    private String publisher;
    private String publishedDate;
    private String description;
    private Integer pageCount;
}
