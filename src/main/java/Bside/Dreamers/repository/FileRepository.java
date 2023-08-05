package Bside.Dreamers.repository;

import Bside.Dreamers.domin.File;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

@Repository
@RequiredArgsConstructor
public class FileRepository {

    private final EntityManager em;

    public File findOne(String originial_file_nm, Long mem_id) {
        File file = null;
        try{
            file = em.createQuery("select f from File f where f.originial_file_nm = :originial_file_nm and f.membr.memberNo = :mem_id", File.class)
                    .setParameter("originial_file_nm", originial_file_nm)
                    .setParameter("mem_id", mem_id)
                    .getSingleResult();
        }catch (NoResultException e){
            return null;
        }finally {
            return file;
        }
    }

    @Transactional
    public File save(File file){
        File fileOne = this.findOne(file.getOriginial_file_nm(),file.getMembr().getMemberNo());
        if (fileOne == null) {
            em.persist(file);
        } else {
            em.merge(fileOne);
        }
        return file;
    }

}
