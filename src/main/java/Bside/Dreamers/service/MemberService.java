package Bside.Dreamers.service;

import Bside.Dreamers.domin.Member;
import Bside.Dreamers.domin.dto.MemberSignupDTO;
import Bside.Dreamers.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true) //읽기 전용
@RequiredArgsConstructor //생성자 주입
public class MemberService {



    private  final MemberRepository memberRepository;

    /**
     * 회원가입
     */
    @Transactional //읽기 전용x
    public Member join(MemberSignupDTO memberSignupDTO) {
        return memberRepository.save(memberSignupDTO.toEntity());
    }



    /**
     * 중복 --
     * */
    private void validateDuplicateMember(Member member) {
        //List<Member> findMembers =
          //      memberRepository.findByName(member.getName());
        /*if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }*/
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    /**
     * 회원 정보 조회
     * */
    public Member getMemberInfo(Long memberId) {
        return memberRepository.findById(memberId);
    }

    /***
     * 회원 정보 수정
     */
    public int updateMemberInfo(Long memberId, Long fileId) {
        return memberRepository.updateById(memberId, fileId);
    }

}
