package vod.repository.jpa;


import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import vod.model.Art;
import vod.model.Director;
import vod.model.Theatre;
import vod.repository.ArtDao;

import java.util.List;

@Repository
//@Primary
public class JpaArtDao implements ArtDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Art> findAll(){
        return entityManager.createQuery(
                "select a from Art a").getResultList();


    }

    @Override
    public Art findById(int id) {
        return entityManager.find(Art.class, id);
    }

    @Override
    public List<Art> findByDirector(Director d){
        return entityManager
                .createQuery("select a from Art a where a.director=:director")
                .setParameter("director", d)
                .getResultList();


    }

    @Override
    public List<Art> findByTheatre(Theatre t){
        return entityManager
                .createQuery("select a from Art a inner join a.theatres theatre where theatre=:theatre")
                .setParameter("theatre", t)
                .getResultList();


    }




    @Override
    public Art add(Art a) {
       entityManager.persist(a);
        return a;
    }
}
