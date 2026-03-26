package vod.web.rest;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import vod.model.Art;
import vod.model.Theatre;
import vod.service.ArtService;
import vod.service.TheatreService;
import vod.web.rest.dto.ArtDTO;

import java.util.List;
import java.util.Locale;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/webapi")
public class ArtRest {

    private final TheatreService theatreService;
    private final ArtService artService;
    private final MessageSource messageSource;
    private final LocaleResolver localeResolver;


    @GetMapping("/arts")
    List<Art> getArts(){
        log.info("about to retrieve arts list");
        List<Art> arts= artService.getAllArts();
        log.info("{} arts found", arts.size());
        return arts;
    }

    @GetMapping("/arts/{id}")
    ResponseEntity<Art> getArt(@PathVariable("id") int id){
        log.info("about to retrieve arts {}", id);
        Art art = artService.getArtById(id);
        log.info("art found: {}", art);
        if(art!=null){
            return ResponseEntity.ok(art);
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }



    @GetMapping("/theatres/{theatreId}/arts")
    ResponseEntity<List<Art>> getArtsPlayedAtTheatre(@PathVariable("theatreId") int theatreId ){
        log.info("about to retrieve arts playing at theatre {}", theatreId);
        Theatre theatre = theatreService.getTheatreById(theatreId);
        if(theatre==null){
            return ResponseEntity.notFound().build();
        }else{
            List<Art> arts=theatreService.getArtsInTheatre(theatre);
            log.info("there's {} arts played at theatre {}", arts.size(), theatre.getName());
            return ResponseEntity.ok(arts);
        }
    }

    @PostMapping("/arts")
    ResponseEntity<?> addArt(@Validated @RequestBody ArtDTO artDTO, Errors errors){
        log.info("about to add new art {}", artDTO);
        if(errors.hasErrors()){
            return ResponseEntity.badRequest().build();
        }
        Art art= new Art();
        art.setTitle(artDTO.getTitle());
        art.setPoster(artDTO.getPoster());
        art.setRating(artDTO.getRating());
        art.setDirector(artService.getDirectorById(artDTO.getDirectorId()));

        art=artService.addArt(art);
        log.info("new art added: {}", art);
        return ResponseEntity
                .created(ServletUriComponentsBuilder
                        .fromCurrentRequestUri()
                        .path("/"+art.getId())
                        .build()
                        .toUri())
                .body(art);
    }

}
