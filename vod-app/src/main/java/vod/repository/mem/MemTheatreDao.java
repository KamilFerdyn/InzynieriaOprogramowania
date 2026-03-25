package vod.repository.mem;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import vod.model.Art;
import vod.model.Theatre;
import vod.repository.TheatreDao;

import java.util.List;
import java.util.stream.Collectors;


@Repository("theatreDao")
//@Component
public class MemTheatreDao implements TheatreDao {

    @Override
    public List<Theatre> findAll() {
        return SampleData.theatres;
    }

    @Override
    public Theatre findById(int id) {
        return SampleData.theatres.stream().filter(c -> c.getId() == id).findFirst().orElse(null);
    }

    @Override
    public List<Theatre> findByArt(Art m) {
        return SampleData.theatres.stream().filter(c -> c.getArts().contains(m)).collect(Collectors.toList());
    }



    @Override
    public Theatre save(Theatre theatre) {
        int maxId=SampleData.theatres.stream()
                .sorted((c1,c2)->c2.getId()-c1.getId())
                .findFirst()
                .map(c->c.getId())
                .orElse(0);
        theatre.setId(maxId+1);
        SampleData.theatres.add(theatre);
        return theatre;
    }
}
