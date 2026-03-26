package vod.web.ui;

import org.springframework.ui.Model;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vod.model.Art;
import vod.model.Director;
import vod.model.Theatre;
import vod.service.ArtService;
import vod.service.TheatreService;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ArtController {

    private final TheatreService theatreService;
    private final ArtService artService;


    @GetMapping("/arts")
    String getArts(Model model, @RequestParam(value = "theatreId", required = false) Integer theatreId,
                   @RequestParam(value = "directorId", required = false) Integer directorId) {
        log.info("about to display arts list in theatre {}", theatreId);
        if(theatreId != null) {
            Theatre theatre = theatreService.getTheatreById(theatreId);
            List<Art> arts = theatreService.getArtsInTheatre(theatre);
            model.addAttribute("arts", arts);
            model.addAttribute("title", "Arts in theatre '" + theatre.getName() + "'");

        }else if(directorId != null) {
            Director director = artService.getDirectorById(directorId);
            List<Art> arts = artService.getArtsByDirector(director);
            model.addAttribute("arts", arts);
            model.addAttribute("title", "Arts direct by '" + director.getLastName()+ "'");

        }
        else{
            List<Art> arts = artService.getAllArts();
            model.addAttribute("arts", arts);
            model.addAttribute("title", "Arts");
        }
        return "artsView";

    }




}
