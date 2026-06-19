package org.example.comic_manga_polica.controller.web;

import org.example.comic_manga_polica.dto.BookShelfRequest;
import org.example.comic_manga_polica.entity.BookShelf;
import org.example.comic_manga_polica.entity.Status;
import org.example.comic_manga_polica.service.BookShelfService;
import org.example.comic_manga_polica.service.ReviewService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequestMapping("/shelf")
public class ShelfPageController {

    private final BookShelfService bookShelfService;
    private final ReviewService reviewService;

    public ShelfPageController(BookShelfService bookShelfService, ReviewService reviewService) {
        this.bookShelfService = bookShelfService;
        this.reviewService = reviewService;
    }

    @GetMapping
    public String prikaziPolicu(@RequestParam(required = false) Status stanje, Model model) {
        List<BookShelf> stavke = bookShelfService.findAll(stanje);

        model.addAttribute("stavke", stavke);
        model.addAttribute("stanje", stanje);

        Map<Long, Integer> stavkeReviews= new HashMap<>();

        for(BookShelf s : stavke){
            reviewService.findOptionalByShelfId(s.getId())
                    .ifPresent(r -> stavkeReviews.put(s.getId(), r.getZvjezdice()));
        }

        model.addAttribute("stavkeReviews", stavkeReviews);

        return "shelf";
    }

    @GetMapping("/{id}")
    public String detaljiPolice(@PathVariable Long id, Model model){
        BookShelf shelf= bookShelfService.findById(id);

        model.addAttribute("shelf", shelf);

        model.addAttribute("review", reviewService.findOptionalByShelfId(id).orElse(null));

        return "shelf-detalji";
    }

    @PostMapping("/dodaj")
    public String dodajNaPolicu(@RequestParam Long comicId){
        BookShelfRequest req= new BookShelfRequest();

        req.setComicId(comicId);
        req.setStanje(Status.ON_SHELF);

        BookShelf saved= bookShelfService.create(req);
        return "redirect:/shelf/" + saved.getId();
    }

    @PostMapping("/status")
    public String promijeniStatus(@RequestParam Long shelfId, @RequestParam Status status){
        bookShelfService.updateStatus(shelfId, status);

        return "redirect:/shelf/" + shelfId;
    }

    @PostMapping("/delete/{id}")
    public String obrisiPolicu(@PathVariable Long id){
        bookShelfService.delete(id);

        return "redirect:/shelf";
    }
}