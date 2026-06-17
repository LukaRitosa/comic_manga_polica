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
}