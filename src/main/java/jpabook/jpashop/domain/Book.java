package jpabook.jpashop.domain;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class Book {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String author;
    @Column(nullable = false)
    private BigDecimal price;

    @Builder
    public Book(String title, String author, BigDecimal price){
        this.title = title;
        this.author = author;
        this.price = price;
    }
}
