package org.example.comic_manga_polica.client;

import org.example.comic_manga_polica.dto.external.jikan.JikanDtos;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.Optional;

@Component
public class JikanClient {

    private final RestClient restClient;

    public JikanClient(@Qualifier("jikanRestClient") RestClient restClient) {
        this.restClient = restClient;
    }

    public Optional<JikanDtos.MangaData> searchManga(String naziv) {
        try {
            JikanDtos.SearchResponse response = restClient.get()
                    .uri(uriBuilder -> uriBuilder
                            .path("/manga")
                            .queryParam("q", naziv)
                            .queryParam("limit", 1)
                            .build())
                    .retrieve()
                    .body(JikanDtos.SearchResponse.class);

            if (response == null || response.data().isEmpty()) {
                return Optional.empty();
            }
            return Optional.of(response.data().get(0));
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}