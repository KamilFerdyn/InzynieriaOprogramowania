package vod.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import vod.model.Art;
import vod.model.Theatre;

import java.util.List;

public interface TheatreService {
//api zwraca nam wszystkie kina
    Theatre getTheatreById(int id);

    List<Theatre> getAllTheatres();

    List<Theatre> getTheatresByArt(Art m);

    List<Art> getArtsInTheatre(Theatre c);

    Theatre addTheatre(Theatre theatre);


}
