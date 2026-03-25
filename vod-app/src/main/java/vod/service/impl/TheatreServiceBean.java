package vod.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import vod.model.Theatre;
import vod.model.Art;
import vod.repository.TheatreDao;
import vod.repository.ArtDao;
import vod.service.TheatreService;

import java.util.List;
import java.util.logging.Logger;
@Service
@Scope("prototype")
public class TheatreServiceBean implements TheatreService {

    private static final Logger log = Logger.getLogger(TheatreService.class.getName());

    private TheatreDao theatreDao;
    private ArtDao artDao;

    //@Autowired
    public TheatreServiceBean(TheatreDao theatreDao, ArtDao artDao) {
        log.info("creating theatre service bean");
        this.theatreDao = theatreDao;
        this.artDao = artDao;
    }

    @Override
    public Theatre getTheatreById(int id) {
        log.info("searching theatre by id " + id);
        return theatreDao.findById(id);
    }

    @Override
    public List<Art> getArtsInTheatre(Theatre c) {
        log.info("searching arts played in theatre " + c.getId());
        return artDao.findByTheatre(c);
    }

    @Override
    public Theatre addTheatre(Theatre theatre) {
        log.info("adding new theatre " + theatre);
        return theatreDao.save(theatre);
    }


    @Override
    public List<Theatre> getAllTheatres() {
        log.info("searching all theatres");
        return theatreDao.findAll();
    }

    @Override
    public List<Theatre> getTheatresByArt(Art m) {
        log.info("searching theatres by art " + m.getId());
        return theatreDao.findByArt(m);
    }



}
