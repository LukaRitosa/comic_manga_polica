package org.example.comic_manga_polica.client;

import org.example.comic_manga_polica.dto.external.comicvine.ComicVineDtos;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.Optional;

@Component
public class ComicVineClient {

    private final RestClient restClient;
    private final String apiKey;

    public ComicVineClient(@Qualifier("comicVineRestClient") RestClient restClient,
                           @Value("${comicvine.api.key}") String apiKey) {
        this.restClient = restClient;
        this.apiKey = apiKey;
    }

    public Optional<ComicVineDtos.Volume> searchComic(String naziv) {
        try {
            ComicVineDtos.SearchResponse response = restClient.get()
                    .uri(uriBuilder -> uriBuilder
                            .path("/volumes/")
                            .queryParam("api_key", apiKey)
                            .queryParam("format", "json")
                            .queryParam("filter", "name:" + naziv)
                            .build())
                    .retrieve()
                    .body(ComicVineDtos.SearchResponse.class);

            if (response == null || response.results().isEmpty()) {
                return Optional.empty();
            }
            return Optional.of(response.results().get(0));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    private Optional<Long> findFirstIssueId(Long volumeId){
        try{
            ComicVineDtos.IssueListResponse response= restClient.get()
                    .uri(uriBuilder -> uriBuilder
                            .path("/issues/")
                            .queryParam("api_key", apiKey)
                            .queryParam("format", "json")
                            .queryParam("filter", "volume:" + volumeId)
                            .queryParam("sort", "issue_number:asc")
                            .queryParam("limit", 1)
                            .queryParam("field_list", "id")
                            .build()
                    ).retrieve()
                    .body(ComicVineDtos.IssueListResponse.class);
            if(response==null || response.results().isEmpty()){
                return Optional.empty();
            }
            return Optional.of(response.results().get(0).id());
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    private Optional<String> findWriterFromIssue(Long issueId){
        try{
            ComicVineDtos.IssueDetailResponse response= restClient.get()
                    .uri(uriBuilder -> uriBuilder
                            .path("/issue/4000-" + issueId + "/")
                            .queryParam("api_key", apiKey)
                            .queryParam("format", "json")
                            .queryParam("field_list", "person_credits")
                            .build()
                    ).retrieve()
                    .body(ComicVineDtos.IssueDetailResponse.class);
            if(response==null || response.results()==null){
                return Optional.empty();
            }
            return response.results().person_credits().stream()
                    .filter(p-> p.role()!=null && p.role().toLowerCase().contains("writer"))
                    .map(ComicVineDtos.PersonCredit::name)
                    .findFirst();
        } catch (Exception e){
            return Optional.empty();
        }
    }

    public Optional<String> findWriter(Long volumeId){
        return findFirstIssueId(volumeId).flatMap(this::findWriterFromIssue);
    }
}