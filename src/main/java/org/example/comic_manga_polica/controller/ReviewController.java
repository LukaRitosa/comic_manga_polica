package org.example.comic_manga_polica.controller;

import org.example.comic_manga_polica.dto.ReviewRequest;
import org.example.comic_manga_polica.entity.Review;
import org.example.comic_manga_polica.service.ReviewService;
import org.example.comic_manga_polica.service.impl.ReviewServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.*;

@RestController
@RequestMapping("api/reviews")
public class ReviewController {
    private ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping
    public ResponseEntity<List<Review>> getAll(@RequestParam(required = false) Integer zvjezdice){
        return ResponseEntity.ok(reviewService.findAll(zvjezdice));
    }

    @GetMapping("{id}")
    public ResponseEntity<Review> getById(@PathVariable Long id){
        return ResponseEntity.ok(reviewService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Review> create(@RequestBody ReviewRequest reviewRequest){
        Review saved= reviewService.create(reviewRequest);

        return ResponseEntity
                .created(URI.create("api/reviews" + saved.getId()))
                .body(saved);
    }

    @PutMapping("{id}")
    public ResponseEntity<Review> update(@PathVariable Long id,
                                        @RequestBody ReviewRequest reviewRequest){
        return ResponseEntity.ok(reviewService.update(id, reviewRequest));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> update(@PathVariable Long id){
        reviewService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
