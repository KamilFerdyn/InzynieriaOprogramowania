package vod.repository.data;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import vod.model.Art;
import vod.model.Theatre;
import vod.repository.TheatreDao;

import java.util.List;


@Repository
@Primary
@RequiredArgsConstructor
public class DataTheatreDao implements TheatreDao {
    private final TheatreRepository repository;

    @Override
    public List<Theatre> findAll() {

        return repository.findAll();
    }

    @Override
    public Theatre findById(int id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<Theatre> findByArt(Art a){
        return repository.findAllByArt(a);
    }

    @Override
    public Theatre save(Theatre theatre) {
        return repository.save(theatre);
    }
}
