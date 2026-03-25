package vod.web.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import vod.model.Art;
import vod.model.Director;
import vod.service.ArtService;
import vod.web.rest.dto.ArtDTO;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class ArtValidator implements Validator {
    private final ArtService artService;

    @Override
    public boolean supports(Class<?> clazz) {return clazz.isAssignableFrom(ArtDTO.class);}

    @Override
    public void validate(Object target, Errors errors) {
        ArtDTO art = (ArtDTO) target;
        Director director= artService.getDirectorById(art.getDirectorId());
        if(director == null) {
            errors.rejectValue("directorId", "art.director.missing");
        }
    }

}
