package vod.repository.mem;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import vod.model.Art;
import vod.repository.ArtDao;
import vod.model.Theatre;
import vod.model.Director;

import java.util.List;
import java.util.stream.Collectors;
@Repository
public class MemArtDao implements ArtDao {
    @Override
    public List<Art> findAll() {
        return SampleData.arts;
    }

    @Override
    public Art findById(int id) {
        return SampleData.arts.stream().filter(m -> m.getId() == id).findFirst().orElse(null);
    }

    @Override
    public List<Art> findByDirector(Director d) {
       return SampleData.arts.stream().filter(m -> m.getDirector() == d).collect(Collectors.toList());
    }

    @Override
    public List<Art> findByTheatre(Theatre c) {
        return SampleData.arts.stream().filter(m -> m.getTheatres().contains(c)).collect(Collectors.toList());
    }

    @Override
    public Art add(Art m) {
        int max = SampleData.arts.stream().max((m1, m2) -> m1.getId() - m2.getId()).get().getId();
        m.setId(++max);
        SampleData.arts.add(m);
        return m;
    }
}
