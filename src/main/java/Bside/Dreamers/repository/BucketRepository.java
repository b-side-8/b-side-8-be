package Bside.Dreamers.repository;

import Bside.Dreamers.domin.Bucket;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class BucketRepository {

    private final EntityManager em;

    @Transactional
    public void regist(Bucket bucket) {
        em.persist(bucket);
    }

    public Bucket findOne(Long no) {
        return em.find(Bucket.class, no);
    }



}
