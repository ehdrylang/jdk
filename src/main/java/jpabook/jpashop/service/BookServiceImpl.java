package jpabook.jpashop.service;

import jpabook.jpashop.domain.item.Book;
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

    }

}
