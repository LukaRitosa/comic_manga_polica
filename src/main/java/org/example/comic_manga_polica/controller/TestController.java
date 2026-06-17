package org.example.comic_manga_polica.controller;

import org.example.comic_manga_polica.client.ComicVineClient;
import org.example.comic_manga_polica.client.JikanClient;
import org.example.comic_manga_polica.entity.Tip;
import org.example.comic_manga_polica.service.ComicService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    private final JikanClient jikanClient;
    private final ComicVineClient comicVineClient;
    private final ComicService comicService;

    public TestController(JikanClient jikanClient, ComicVineClient comicVineClient, ComicService comicService) {
        this.jikanClient = jikanClient;
        this.comicVineClient = comicVineClient;
        this.comicService= comicService;
    }

    @GetMapping("/jikan")
    public Object testJikan(@RequestParam String naziv) {
        return jikanClient.searchManga(naziv);
    }

    @GetMapping("/comicvine")
    public Object testComicVine(@RequestParam String naziv) {
        return comicVineClient.searchComic(naziv);
    }

    @GetMapping("/comicvine-writer")
    public Object testWriter(@RequestParam Long volumeId) {
        return comicVineClient.findWriter(volumeId);
    }

    @GetMapping("/create-comic")
    public Object testCreateComic(@RequestParam String naziv, @RequestParam Tip tip) {
        return comicService.createComicFromApi(naziv, tip);
    }
}