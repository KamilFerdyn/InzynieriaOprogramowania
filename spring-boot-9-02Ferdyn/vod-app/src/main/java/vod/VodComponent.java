package vod;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import vod.model.Theatre;
import vod.service.TheatreService;

import java.util.Arrays;
import java.util.List;
//import void.Service.TheatreService
@Component
@Slf4j
public class VodComponent implements CommandLineRunner, ApplicationListener<ContextRefreshedEvent> {



    private final TheatreService theatreService;

    public VodComponent(TheatreService theatreService){
        this.theatreService = theatreService;

    }

    @PostConstruct
    public void init(){
        List<Theatre> theatres = this.theatreService.getAllTheatres();
        log.info("{} theatres found", theatres.size());
        theatres.forEach(theatre -> log.info("{}", theatre));
    }

    @Override
    public void run(String... args) throws Exception {
    log.info("porgram arguments: {}  ", Arrays.toString(args));
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
    log.info("on context refreshed");
    List<Theatre> theatres = this.theatreService.getAllTheatres();
    log.info("{} theatres found", theatres.size());
    theatres.forEach(theatre -> log.info("{}", theatre));
    }

    @EventListener
    public void EventListener(ContextRefreshedEvent event){
        log.info("on context refreshed");
    }
}
