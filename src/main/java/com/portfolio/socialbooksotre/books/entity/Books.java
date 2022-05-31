package com.portfolio.socialbooksotre.books.entity;

import com.portfolio.socialbooksotre.commons.BaseTime;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Table(name = "social_books")
@Entity
public class Books extends BaseTime {

    @Column(name = "books_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;

    private String title;
    private String filename;
    private String writer;
    private String publisher;
    private int price;
    private String isbn;
    private String content;
    private String publishDate;
    private int deliveryFee;
    private int amount;


}
