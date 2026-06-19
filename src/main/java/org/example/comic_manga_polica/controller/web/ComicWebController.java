package org.example.comic_manga_polica.controller.web;

import org.example.comic_manga_polica.entity.Comic;
import org.example.comic_manga_polica.service.ComicService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;

@Controller
@RequestMapping("/comic")
public class ComicWebController {
    private final ComicService comicService;

    public ComicWebController(ComicService comicService) {
        this.comicService = comicService;
    }

    @GetMapping("/{id}")
    public String detalji(@PathVariable Long id, Model model){
        model.addAttribute("comic", comicService.findById(id));

        return "comic-detalji";
    }

    @GetMapping("/sve")
    public String prikaziComics(Model model){
        List<Comic> comics= comicService.findNotOnShelf();
        model.addAttribute("comics", comics);

        return "comics";
    }
}
