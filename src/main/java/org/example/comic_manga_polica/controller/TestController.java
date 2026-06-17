package org.example.comic_manga_polica.controller;

import org.example.comic_manga_polica.client.ComicVineClient;
import org.example.comic_manga_polica.client.JikanClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    private final JikanClient jikanClient;
    private final ComicVineClient comicVineClient;

    public TestController(JikanClient jikanClient, ComicVineClient comicVineClient) {
        this.jikanClient = jikanClient;
        this.comicVineClient = comicVineClient;
    }

    @GetMapping("/jikan")
    public Object testJikan(@RequestParam String naziv) {
        return jikanClient.searchManga(naziv);
    }

    @GetMapping("/comicvine")
    public Object testComicVine(@RequestParam String naziv) {
        return comicVineClient.searchComic(naziv);
    }
}