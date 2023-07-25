package Bside.Dreamers.service;

import Bside.Dreamers.domin.Bucket;
import Bside.Dreamers.domin.Category;
import Bside.Dreamers.domin.Member;
import Bside.Dreamers.domin.dto.BucketRegistDTO;
import Bside.Dreamers.domin.dto.BucketResponseDTO;
import Bside.Dreamers.repository.BucketRepository;
import Bside.Dreamers.repository.CategoryRepository;
import Bside.Dreamers.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
//        엔티티 조회
//        Member member = memberRepository.findById(memberId);
//        Bucket bucket = bucketRepository.findOne(bucketId);
//
        return null;
    }

    @Transactional
    public void registBucket(BucketRegistDTO bucketRegistDTO){
        Member member = memberRepository.findById(bucketRegistDTO.getMemberId());
        if(member == null){
            new Exception("해당 회원이 존재하지 않습니다.");
        }
        Category category = categoryRepository.findOne(bucketRegistDTO.getCategoryId());
        if(category == null){
            new Exception("해당 카테고리가 존재하지 않습니다.");
        }


        bucketRepository.regist(bucketRegistDTO.toEntity(member, category));
    }

    @Transactional
    public List<BucketResponseDTO> findBucketByMemberNo(Long memberNo){
        return bucketRepository.findBucketByMemberNo(memberNo);
    }

}
