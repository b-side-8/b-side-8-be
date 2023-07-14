package Bside.Dreamers.repository;

import Bside.Dreamers.domin.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor //생성자 주입
public class MemberRepository {


    private final EntityManager em;


    @Transactional
    public Member save(Member member) {
        Member memberInfo = this.findById(member.getId());
        if(memberInfo == null) {
            em.persist(member);
        } else {
            em.merge(memberInfo);
        }
        return member;
    }

    public Member findById(Long id) {
        return em.createQuery("select m from Member m where m.id = :id", Member.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }

    public List<Member> findByName(String name) {
        return em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
    }




}
