package Bside.Dreamers.repository;

import Bside.Dreamers.domin.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor //생성자 주입
public class CategoryRepository {

    private final EntityManager em;


    public Category findOne(Long no) {
        return em.find(Category.class, no);
    }

    public List<Category> findAll() {
        return em.createQuery("select i from Category i",Category.class).getResultList();
    }

}
