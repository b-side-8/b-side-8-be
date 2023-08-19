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

    public File findOne(String originial_file_nm) {
        File file = null;
        try{
            file = em.createQuery("select f from File f where f.originial_file_nm = :originial_file_nm", File.class)
                    .setParameter("originial_file_nm", originial_file_nm)
                    .getSingleResult();
        }catch (NoResultException e){
            return null;
        }finally {
            return file;
        }
    }

    @Transactional
    public File save(File file){
        File fileOne = this.findOne(file.getOriginial_file_nm());
        if (fileOne == null) {
            em.persist(file);
        } else {
            em.merge(fileOne);
        }
        return file;
    }


    public File findOneName(Long fileId) {
        File file = null;
        try{
            file = em.createQuery("select f from File f where f.id = :file_id", File.class)
                    .setParameter("file_id", fileId)
                    .getSingleResult();
        }catch (NoResultException e){
            return null;
        }finally {
            return file;
        }
    }


    public File findOneId(String fileNm) {
        File file = null;
        try{
            file = em.createQuery("select f from File f where f.originial_file_nm = :fileNm", File.class)
                    .setParameter("fileNm", fileNm)
                    .getSingleResult();
        }catch (NoResultException e){
            return null;
        }finally {
            return file;
        }
    }

}
