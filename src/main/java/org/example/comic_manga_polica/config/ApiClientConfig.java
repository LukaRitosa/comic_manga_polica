package org.example.comic_manga_polica.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class ApiClientConfig {

    @Bean
    public RestClient jikanRestClient(@Value("${jikan.api.base-url}") String url) {
        return RestClient.builder()
                .baseUrl(url)
                .build();
    }

    @Bean
    public RestClient comicVineRestClient(@Value("${comicvine.api.base-url}") String url) {
        return RestClient.builder()
                .baseUrl(url)
                .defaultHeader("User-Agent", "comic-manga-polica-app")
                .build();
    }
}