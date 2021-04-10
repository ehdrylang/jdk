package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderRepository {
    private final EntityManager em;

    public void save(Order order) {
        em.persist(order);
    }

    public Order findOne(Long id) {
        return em.find(Order.class, id);
    }

//    public List<Order> findAll(OrderSearch orderSearch) {
//        em.createQuery("select o from Order o join o.member"
//                + "where o.status =:status"
//                + "and m.name like :name"
//                , Order.class)
//            .setParameter("status", orderSearch.getOrderStatus())
//            .setParameter("name", orderSearch.getMemberName())
//            .setMaxResults(1000)
//            .getResultList();
//    }

    /**
     * JPA Criteria 표준이지만 권장하지 않음
     */
    public List<Order> findAllByCriteria(OrderSearch orderSearch) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Order> cq = cb.createQuery(Order.class);
        Root<Order> root = cq.from(Order.class);
        Join<Object, Object> m = root.join("member", JoinType.INNER);
        List<Predicate> criteria = new ArrayList<>();
        //주문 상태
        if(orderSearch.getOrderStatus() != null) {
            Predicate status = cb.equal(root.get("status"), orderSearch.getOrderStatus());
            criteria.add(status);
        }

    }
}
