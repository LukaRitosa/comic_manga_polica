package org.example.comic_manga_polica.controller.web;


import org.example.comic_manga_polica.dto.ReviewRequest;
import org.example.comic_manga_polica.entity.BookShelf;
import org.example.comic_manga_polica.repository.BookShelfRepository;
import org.example.comic_manga_polica.service.BookShelfService;
import org.example.comic_manga_polica.service.ReviewService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/review")
public class ReviewPageController {
    private final BookShelfService bookShelfService;
    private ReviewService reviewService;

    public ReviewPageController(ReviewService reviewService, BookShelfService bookShelfService) {
        this.reviewService = reviewService;
        this.bookShelfService = bookShelfService;
    }


    @PostMapping("/dodaj")
    public String dodajRecenziju(
            @RequestParam Long shelfId,
            @RequestParam Integer zvjezdice,
            @RequestParam String komentar
            ){
        ReviewRequest reviewRequest= new ReviewRequest();

        reviewRequest.setShelfId(shelfId);
        reviewRequest.setZvjezdice(zvjezdice);
        reviewRequest.setKomentar(komentar);

        reviewService.create(reviewRequest);

        return "redirect:/shelf/" + shelfId;
    }

    @GetMapping("/dodaj/{shelfId}")
    public String prikaziFormuZaDodavanje(@PathVariable Long shelfId, Model model) {
        BookShelf shelf = bookShelfService.findById(shelfId);
        model.addAttribute("shelf", shelf);
        return "review-dodaj";
    }

    @PostMapping("/uredi/{id}")
    public String urediRecenziju(
            @PathVariable Long id,
            @RequestParam  Long shelfId,
            @RequestParam Integer zvjezdice,
            @RequestParam String komentar) {
        ReviewRequest request= new ReviewRequest();
        request.setShelfId(shelfId);
        request.setZvjezdice(zvjezdice);
        request.setKomentar(komentar);

        reviewService.update(id, request);
        return "redirect:/shelf/" + shelfId;
    }

    @PostMapping("/obrisi/{id}")
    public String obrisiRecenziju(
            @PathVariable Long id,
            @RequestParam  Long shelfId) {

        reviewService.delete(id);
        return "redirect:/shelf/" + shelfId;
    }
}
