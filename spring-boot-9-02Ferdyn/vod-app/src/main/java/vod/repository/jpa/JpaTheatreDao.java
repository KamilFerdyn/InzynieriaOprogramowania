package vod.repository.jpa;


import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import vod.model.Art;
import vod.model.Theatre;
import vod.repository.TheatreDao;

import java.util.List;

@Repository
public class JpaTheatreDao implements TheatreDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Theatre> findAll(){

        return entityManager
                .createQuery("select t from Theatre t")
                .getResultList();

    }

    @Override
    public Theatre findById(int id){
        return entityManager.find(Theatre.class, id);
    }

    @Override
    public List<Theatre> findByArt(Art a){
        return entityManager
                .createQuery("select t from Theatre t inner join t.arts art where art=:art")
                .setParameter("art", a)
                .getResultList();
    }

    @Override
    public Theatre save(Theatre theatre) {
        entityManager.persist(theatre);
        return theatre;
    }


}
