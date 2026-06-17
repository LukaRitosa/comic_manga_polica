package org.example.comic_manga_polica.dto.external.comicvine;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

public class ComicVineDtos {

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record SearchResponse(List<Volume> results) {}

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record Volume(String name, String start_year, Image image, Publisher publisher) {}

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record Image(String medium_url) {}

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record Publisher(String name) {}
}
