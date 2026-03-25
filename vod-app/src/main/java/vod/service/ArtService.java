package vod.service;

import vod.model.Art;
import vod.model.Director;

import java.util.List;

public interface ArtService {


    List<Art> getAllArts();

    List<Art> getArtsByDirector(Director d);

    Art getArtById(int id);

    Art addArt(Art m);


    List<Director> getAllDirectors();

    Director getDirectorById(int id);

    Director addDirector(Director d);
}
