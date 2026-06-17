package org.example.comic_manga_polica.dto.external.comicvine;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

public class ComicVineDtos {

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record SearchResponse(List<Volume> results) {}

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record Volume(Long id, String name, String start_year, Image image, Publisher publisher) {}

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record Image(String medium_url) {}

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record Publisher(String name) {}


    @JsonIgnoreProperties(ignoreUnknown = true)
    public record IssueListResponse(List<IssueListItem> results) {}

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record IssueListItem(Long id) {}

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record IssueDetailResponse(IssueDetail results) {}

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record IssueDetail(List<PersonCredit> person_credits) {}

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record PersonCredit(String name, String role) {}
}
