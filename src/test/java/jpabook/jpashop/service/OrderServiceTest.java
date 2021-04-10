package jpabook.jpashop.service;

import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderStatus;
import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.exception.NotEnoughStockException;
import jpabook.jpashop.repository.OrderRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@SpringBootTest
@Transactional
class OrderServiceTest {
    @Autowired
    EntityManager em;
    @Autowired
    OrderService orderService;
    @Autowired
    OrderRepository orderRepository;
    @Test
    public void 상품주문() {
        Member member = createMember();
        Item book = createBook(10000, "시골JPA", 10);

        int orderCount = 2;
        //when
        Long orderId = orderService.order(member.getId(), book.getId(), orderCount);
        //then
        Order getOrder = orderRepository.findOne(orderId);
        Assertions.assertThat(getOrder.getStatus()).isEqualTo(OrderStatus.ORDER);
        Assertions.assertThat(getOrder.getOrderItems().size()).isEqualTo(1);
        Assertions.assertThat(getOrder.getTotalPrice()).isEqualTo(10000 * orderCount);
        Assertions.assertThat(book.getStockQuantity()).isEqualTo(8);
    }
    private Member createMember() {
        Member member = new Member();
        member.setName("회원1");
        member.setAddress(new Address("서울", "강가", "123-123"));
        em.persist(member);
        return member;
    }
    private Item createBook(int price, String name, int stockQuantity) {
        Item book = Book.builder()
                .title("시골JPA")
                .author("jdk")
                .isbn("1231211121")
                .build();
        book.setPrice(price);
        book.setName(name);
        book.setStockQuantity(stockQuantity);
        em.persist(book);
        return book;
    }
    @Test
    public void 주문취소() {
        Member member = createMember();
        Item book = createBook(10000,"시골 JPA", 10);
        int orderCount = 2;
        Long id = orderService.order(member.getId(), book.getId(), orderCount);
        //when
        orderService.cancelOrder(id);
        //then
        Order getOrder = orderRepository.findOne(id);
        Assertions.assertThat(getOrder.getStatus()).isEqualTo(OrderStatus.CANCEL);
        Assertions.assertThat(book.getStockQuantity()).isEqualTo(10);
    }

    @Test
    public void 상품주문_재고수량초과() {
        Member member = createMember();
        Item book = createBook(10000, "시골JPA", 10);

        int orderCount = 11;
        Assertions.assertThatThrownBy(()->orderService.order(member.getId(), book.getId(), orderCount))
                .isInstanceOf(NotEnoughStockException.class);
    }
}