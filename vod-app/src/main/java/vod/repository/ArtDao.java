package vod.repository;

import vod.model.Theatre;
import vod.model.Director;
import vod.model.Art;

import java.util.List;

public interface ArtDao {

    List<Art> findAll();

    Art findById(int id);

    List<Art> findByDirector(Director d);

    List<Art> findByTheatre(Theatre t);

    Art add(Art a);

}
