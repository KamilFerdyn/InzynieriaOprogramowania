package vod.repository.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import vod.model.Art;
import vod.model.Theatre;

import java.util.List;

public interface TheatreRepository extends JpaRepository<Theatre, Integer> {
    List<Theatre> findAllByNameContaining(String name);


    @Query("select t from Theatre t inner join t.arts art where art=:art")
    List<Theatre> findAllByArt(@Param("art") Art art);

}
