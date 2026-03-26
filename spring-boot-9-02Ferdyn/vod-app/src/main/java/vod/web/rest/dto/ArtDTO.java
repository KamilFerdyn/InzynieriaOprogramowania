package vod.web.rest.dto;

import lombok.Data;

@Data
public class ArtDTO {
    private String title;
    private String poster;
    private int directorId;
    private float rating;
}
