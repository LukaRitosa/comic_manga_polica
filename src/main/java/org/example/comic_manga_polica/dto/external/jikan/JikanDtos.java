package org.example.comic_manga_polica.dto.external.jikan;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

public class JikanDtos {

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record SearchResponse(List<MangaData> data) {}

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record SingleResponse(MangaData data) {}

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record MangaData(Long mal_id, String title, Images images, List<Person> authors, Published published) {}

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record Images(ImageSet jpg) {}

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record ImageSet(String large_image_url) {}

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record Person(String name) {}

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record Published(String from) {}
}
