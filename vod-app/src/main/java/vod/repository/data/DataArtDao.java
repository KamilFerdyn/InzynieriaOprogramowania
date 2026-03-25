package vod.repository.data;

import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import vod.model.Art;
import vod.model.Director;
import vod.model.Theatre;
import vod.repository.ArtDao;

import java.util.List;


@Repository
@RequiredArgsConstructor
@Primary
public class DataArtDao implements ArtDao {
    private final ArtRepository repository;

    @Override
    public List<Art> findAll(){return repository.findAll();}

    @Override
    public Art findById(int id){ return repository.findById(id).orElse(null);}

    @Override
    public List<Art> findByDirector(Director d){return repository.findAllByDirector(d);}

    @Override
    public List<Art>findByTheatre(Theatre t){ return repository.findAllByTheatresContaining(t);}

    @Transactional(propagation = Propagation.MANDATORY)
    @Override
    public Art add(Art a){return repository.save(a);}






}
