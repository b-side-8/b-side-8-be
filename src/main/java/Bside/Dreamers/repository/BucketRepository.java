package Bside.Dreamers.repository;

import Bside.Dreamers.domin.Bucket;
import Bside.Dreamers.domin.dto.BucketResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

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

    public List<BucketResponseDTO> findBucketByMemberNo(Long memberNo){
         List<Bucket> bucketList = em.createQuery("select b from Bucket b where b.member.memberNo = :member", Bucket.class)
                .setParameter("member", memberNo)
                .getResultList();
         List<BucketResponseDTO> bucketDtoList = new ArrayList<>();

         for(Bucket bucket : bucketList){
             BucketResponseDTO dto = BucketResponseDTO.builder()
                     .title(bucket.getTitle())
                     .detail(bucket.getDetail())
                     .endDt(bucket.getEndDt())
                     .registDt(bucket.getRegistDt())
                     .achvYn(bucket.getAchvYn())
                     .memberId(bucket.getMember().getId())
                     .categoryId(bucket.getCategory().getNo())
                     .build();

             bucketDtoList.add(dto);
         }

         return bucketDtoList;
    }

    public BucketResponseDTO findBucketByBucketNo(Long bucketNo){
        Bucket bucket = em.find(Bucket.class, bucketNo);

        BucketResponseDTO dto = BucketResponseDTO.builder()
                .title(bucket.getTitle())
                .detail(bucket.getDetail())
                .endDt(bucket.getEndDt())
                .registDt(bucket.getRegistDt())
                .achvYn(bucket.getAchvYn())
                .memberId(bucket.getMember().getId())
                .categoryId(bucket.getCategory().getNo())
                .build();

        return dto;
    }



}
