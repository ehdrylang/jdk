package jpabook.jpashop.domain;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class Book {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @Length(min = 5)
    private String author;
    private BigDecimal price;

    @Builder
    public Book(String title, String author, BigDecimal price){
        this.title = title;
        this.author = author;
        this.price = price;
    }
}
