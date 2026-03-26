package vod.repository.data;

import org.springframework.data.jpa.repository.JpaRepository;
import vod.model.Art;
import vod.model.Director;
import vod.model.Theatre;

import java.util.List;

public interface ArtRepository extends JpaRepository<Art, Integer> {

    List<Art> findAllByDirector(Director d);

    List<Art> findAllByTheatresContaining(Theatre t);



}

