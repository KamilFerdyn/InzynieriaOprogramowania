package vod.web.ui;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vod.model.Art;
import vod.model.Theatre;
import vod.service.ArtService;
import vod.service.TheatreService;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class TheatreController {
    private final TheatreService theatreService;
    private final ArtService artService;

    @GetMapping("/theatres")
    String getTheatres(Model model,  @RequestParam(value = "artId", required = false) Integer artId) {

         log.info("about to display theatres list playing art {}", artId);
            if(artId!=null){
                Art art = artService.getArtById(artId);
                List<Theatre> theatres = theatreService.getTheatresByArt(art);
                model.addAttribute("theatres", theatres);
                model.addAttribute("title", "Theatres playin '" + art.getTitle() + "'");
            }else {
                List<Theatre>theatres = theatreService.getAllTheatres();
                model.addAttribute("theatres", theatres);
                model.addAttribute("title", "Theatres");
            }
            return "theatresView";

    }


}
