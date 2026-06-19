package org.example.comic_manga_polica.controller.web;

import org.example.comic_manga_polica.dto.ComicPreview;
import org.example.comic_manga_polica.entity.Comic;
import org.example.comic_manga_polica.entity.Tip;
import org.example.comic_manga_polica.exeption.ApiUnavailableException;
import org.example.comic_manga_polica.service.ComicService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/pretraga")
public class PretragaController {

    private final ComicService comicService;

    public PretragaController(ComicService comicService) {
        this.comicService = comicService;
    }

    @GetMapping
    public String prikaziFormu() {
        return "pretraga";
    }

    @GetMapping("/rezultati")
    public String prikaziRezultate(@RequestParam String naziv, @RequestParam Tip tip, Model model) {
        try{
            List<ComicPreview> rezultati = comicService.searchPreview(naziv, tip);


            model.addAttribute("rezultati", rezultati);
            model.addAttribute("naziv", naziv);
            model.addAttribute("apiError", null);
        } catch (ApiUnavailableException e) {
            model.addAttribute("rezultati", List.of());
            model.addAttribute("naziv", naziv);
            model.addAttribute("apiError", e.getMessage());
        }
        return "pretraga-rezultati";
    }

    @GetMapping("/spremi")
    public String spremi(@RequestParam Tip tip, @RequestParam Long externalId) {
        Comic comic= comicService.confirmAndSave(tip, externalId);

        return "redirect:/comic/" + comic.getId();
    }
}