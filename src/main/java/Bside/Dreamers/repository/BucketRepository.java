package Bside.Dreamers.repository;

import Bside.Dreamers.domin.Bucket;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class BucketRepository {

    private final EntityManager em;

    public void regist(Bucket bucket) {
        em.persist(bucket);
    }

    public Bucket findOne(Long no) {
        return em.find(Bucket.class, no);
    }



}
