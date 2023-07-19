package Bside.Dreamers.service;

import Bside.Dreamers.domin.Bucket;
import Bside.Dreamers.domin.Member;
import Bside.Dreamers.domin.dto.BucketRegistDTO;
import Bside.Dreamers.repository.BucketRepository;
import Bside.Dreamers.repository.CategoryRepository;
import Bside.Dreamers.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BucketService {

    private final MemberRepository memberRepository;
    private final BucketRepository bucketRepository;
    private final CategoryRepository categoryRepository;

    /** 버킷리스트 */
    @Transactional
    public Long order(Long memberId, Long bucketId, int count) {
//엔티티 조회
        Member member = memberRepository.findById(memberId);
        Bucket bucket = bucketRepository.findOne(bucketId);

        return null;
    }

    public void regist(BucketRegistDTO bucketRegistDTO){
        bucketRepository.regist(bucketRegistDTO.toEntity());
    }

}
