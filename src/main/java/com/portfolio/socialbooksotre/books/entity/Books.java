package com.portfolio.socialbooksotre.books.entity;

import com.portfolio.socialbooksotre.commons.BaseTime;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@DynamicUpdate
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
    @Builder
    public Books(long id, String title, String filename, String writer, String publisher, int price, String isbn,
                 String content, String publishDate, int deliveryFee, int amount) {
        this.id = id;
        this.title = title;
        this.filename = filename;
        this.writer = writer;
        this.publisher = publisher;
        this.price = price;
        this.isbn = isbn;
        this.content = content;
        this.publishDate = publishDate;
        this.deliveryFee = deliveryFee;
        this.amount = amount;
    }
}







