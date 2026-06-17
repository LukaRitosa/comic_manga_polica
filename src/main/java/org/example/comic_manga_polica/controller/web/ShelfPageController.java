package org.example.comic_manga_polica.controller.web;

import org.example.comic_manga_polica.entity.BookShelf;
import org.example.comic_manga_polica.service.BookShelfService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/shelf")
public class ShelfPageController {

    private final BookShelfService bookShelfService;

    public ShelfPageController(BookShelfService bookShelfService) {
        this.bookShelfService = bookShelfService;
    }

    @GetMapping
    public String prikaziPolicu(Model model) {
        List<BookShelf> stavke = bookShelfService.findAll(null);
        model.addAttribute("stavke", stavke);
        return "shelf";
    }
}