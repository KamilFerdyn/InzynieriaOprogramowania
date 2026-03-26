package vod.service;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import vod.repository.TheatreDao;
import vod.repository.ArtDao;
import vod.repository.mem.MemTheatreDao;
import vod.repository.mem.MemArtDao;
import vod.model.Theatre;
import vod.service.impl.TheatreServiceBean;

import java.util.List;

public class VodServiceMain {

    public static void main(String[] args) {
        System.out.println("Let's find theatres!");

        ApplicationContext context =new AnnotationConfigApplicationContext("vod");
        TheatreService service = context.getBean(TheatreService.class);
        TheatreService service2 = context.getBean(TheatreService.class);

        List<Theatre> theatres=service.getAllTheatres();
        System.out.println(theatres.size()+" theatres found: ");
        theatres.forEach(System.out::println);

        String foo = context.getBean(String.class);
        System.out.println("foo string: " + foo);


    }
}
