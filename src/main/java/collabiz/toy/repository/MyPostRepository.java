package collabiz.toy.repository;

import collabiz.toy.entity.MyPost;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

/**
 * by.dahae
 */
@Repository
@RequiredArgsConstructor
public class MyPostRepository {

    private final EntityManager em;

    public void save(MyPost order) {
        em.persist(order);
    }

    public MyPost findOne(Long id) {
        return em.find(MyPost.class, id);
    }

    /**
     * comment from dahae. 여기에 검색 로직 추가 하시면 될 것 같습니다..
     * **/
    // public List<Order> findAll(OrderSearch orderSearch) { ... }
}