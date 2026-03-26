package vod.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import vod.model.Art;
import vod.model.Theatre;
import vod.repository.TheatreDao;
import vod.repository.DirectorDao;
import vod.repository.ArtDao;
import vod.model.Director;
import vod.service.ArtService;

import java.util.List;
import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
public class ArtServiceBean implements ArtService {

    private static final Logger log = Logger.getLogger(ArtService.class.getName());
    private final DirectorDao directorDao;
    private final ArtDao artDao;
    private final TheatreDao theatreDao;
    private final PlatformTransactionManager transactionManager;



//    @Autowired
//    private DataSourceTransactionManager transactionManager;
//
//    @Autowired
//    public void setDirectorDao(DirectorDao directorDao) {
//        this.directorDao = directorDao;
//    }
//
//    @Autowired
//    private DirectorDao directorDao;
//    private TheatreDao theatreDao;
//    private ArtDao artDao;

//    public ArtServiceBean(DirectorDao directorDao, TheatreDao theatreDao, ArtDao artDao) {
//        this.directorDao = directorDao;
//        this.theatreDao = theatreDao;
//        this.artDao = artDao;
//    }

    public List<Art> getAllArts() {
        log.info("searching all arts...");
        return artDao.findAll();
    }

    public List<Art> getArtsByDirector(Director d) {
        log.info("serching arts by diretor " + d.getId());
        return artDao.findByDirector(d);
    }

    public List<Art> getArtsInTheatre(Theatre t) {
        log.info("searching arts played in theatre " + t.getId());
        return artDao.findByTheatre(t);
    }

    public Art getArtById(int id) {
        log.info("searching arts by id " + id);
        return artDao.findById(id);
    }

    public List<Theatre> getAllTheatres() {
        log.info("searching all theatre");
        return theatreDao.findAll();
    }

    public List<Theatre> getTheatresByArt(Art m) {
        log.info("searching theatres by art " + m.getId());
        return theatreDao.findByArt(m);
    }

    public Theatre getTheatreById(int id) {
        log.info("searching theatre by id " + id);
        return theatreDao.findById(id);
    }

    public List<Director> getAllDirectors() {
        log.info("searching all directors");
        return directorDao.findAll();
    }

    public Director getDirectorById(int id) {
        log.info("searching director by id " + id);
        return directorDao.findById(id);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Art addArt(Art a) {
        log.info("about to add art " + a);
        TransactionStatus ts= transactionManager.getTransaction(new DefaultTransactionDefinition());
        try{
            a=artDao.add(a);
            if (a.getTitle().equals("Apocalypse now")){
                throw  new RuntimeException("not yet");
            }
            transactionManager.commit(ts);
        }catch (RuntimeException e){
            transactionManager.rollback(ts);
            throw e;
        }
        return a;
    }

    @Override
    public Director addDirector(Director d) {
        log.info("about to add director " + d);
        return directorDao.add(d);
    }

}
