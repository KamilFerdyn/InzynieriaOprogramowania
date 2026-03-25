package vod.web.rest;


import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.LocaleResolver;
import vod.model.Art;
import vod.model.Theatre;
import vod.service.ArtService;
import vod.service.TheatreService;

import java.util.List;
import java.util.Locale;


@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/webapi")
public class TheatreRest {

    private final TheatreService theatreService;
    private final ArtService artService;
    private final MessageSource messageSource;
    private final LocaleResolver localeResolver;
    private final TheatreValidator validator;

    @InitBinder
    void initBinder (WebDataBinder binder){binder.addValidators(validator);}


    @GetMapping("/theatres")
    List<Theatre> getTheatres(@RequestParam(value= "phrase", required=false) String phrase,
                              @RequestHeader(value="custom-header", required=false) String customHeader,
                              @CookieValue(value = "some-cookie", required = false) String someCookie) {
        log.info("about to retrieve theatres list");
        log.info("phrase param: {}", phrase);
        log.info("custom-header param: {}", customHeader);
        log.info("some cookie: {}", someCookie);

        if(phrase !=null && phrase.equals("foo")){
            throw new IllegalArgumentException("Foo!");
        }



        List<Theatre> theatres = theatreService.getAllTheatres();
        log.info("{} theatres found", theatres.size());
        return theatres;
    }

    @GetMapping("/theatres/{id}")
    ResponseEntity<Theatre> getTheatre(@PathVariable("id") int id){
        log.info("about to retrieve theatre {}", id);
        Theatre theatre= theatreService.getTheatreById(id);
        log.info("theatre found {}", theatre);
        if(theatre!=null){
            return ResponseEntity.ok(theatre);
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


    @GetMapping("/arts/{artId}/theatres")
    ResponseEntity<List<Theatre>> getTheatresPlayingArt(@PathVariable("artId") int artId){
        log.info("about to retrieve arts {}", artId);
        Art art = artService.getArtById(artId);
        if(art==null){
            return ResponseEntity.noContent().build();

        }else {
            List<Theatre> theatres=theatreService.getTheatresByArt(art);
            log.info("there's {} theatres playing act {}", theatres.size(), art.getTitle());
            return ResponseEntity.ok(theatres);
        }
    }


    @PostMapping("/theatres")
    ResponseEntity<?> addTheatre(@Validated @RequestBody Theatre theatre, Errors errors, HttpServletRequest request){
        log.info("about to add new theatre {}", theatre);

        if(errors.hasErrors()){
            Locale locale = localeResolver.resolveLocale(request);
            String errorMessage=errors.getAllErrors().stream()
                    .map(oe->messageSource.getMessage(oe.getCode(), new Object[0], locale))
                    .reduce("errors:\n", (accu, oe)->accu+oe+"\n");
            return ResponseEntity.badRequest().body(errorMessage);


        }

        theatre = theatreService.addTheatre(theatre);
        log.info("new theatre added {}", theatre);
        return ResponseEntity.status(HttpStatus.CREATED).body(theatre);
    }


}
