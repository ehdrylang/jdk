package jpabook.jpashop.service;

import jpabook.jpashop.domain.Book;
import jpabook.jpashop.domain.BookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;

@Service
@Slf4j
@RequiredArgsConstructor
public class BookServiceImpl {
    private final BookRepository bookRepository;
    @PostConstruct
    public void init(){
        Book book = Book.builder()
                .price(BigDecimal.TEN)
                .title("Spring Boot")
                .author("jdk")
                .build();
        log.error("create book!");
        Book savedBook = bookRepository.save(book);
        log.error("savedBook = " + savedBook);

        Book nullBook = Book.builder()
                .price(null)
                .title("Spring Boot 2")
                .author("jdk")
                .build();
        log.error("create nullBook");
        bookRepository.save(nullBook);
        log.error("nullBook = " + nullBook);
    }

}
