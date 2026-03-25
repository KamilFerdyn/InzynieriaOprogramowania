package vod.repository;

import vod.model.Art;
import vod.model.Theatre;

import java.util.List;

public interface TheatreDao {

    List<Theatre> findAll();

    Theatre findById(int id);

    List<Theatre> findByArt(Art a);

    Theatre save(Theatre theatre);

}
