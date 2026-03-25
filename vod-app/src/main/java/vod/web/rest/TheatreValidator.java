package vod.web.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import vod.model.Theatre;
import vod.service.TheatreService;


@Component
@RequiredArgsConstructor
public class TheatreValidator implements Validator {

    private final TheatreService theatreService;

    @Override
    public boolean supports(Class<?> clazz){
        return clazz.isAssignableFrom(Theatre.class);
    }

    @Override
    public void validate(Object target, Errors errors){
        Theatre validatedTheatre= (Theatre) target;

        boolean duplicated = theatreService.getAllTheatres().stream()
                .anyMatch(theatre -> theatre.getName().equalsIgnoreCase(validatedTheatre.getName()));
        if(duplicated){
            errors.rejectValue("name", "theatre.name.duplicated");
        }
    }
}
