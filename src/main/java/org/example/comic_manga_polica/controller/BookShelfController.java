package org.example.comic_manga_polica.controller;

import org.example.comic_manga_polica.dto.BookShelfRequest;
import org.example.comic_manga_polica.entity.BookShelf;
import org.example.comic_manga_polica.entity.Status;
import org.example.comic_manga_polica.service.BookShelfService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api/shelf")
public class BookShelfController {
    private BookShelfService bookShelfService;

    public BookShelfController(BookShelfService bookShelfService) {
        this.bookShelfService = bookShelfService;
    }

    @GetMapping
    public ResponseEntity<List<BookShelf>> getAll(@RequestParam(required = false) Status stanje) {
        return ResponseEntity.ok(bookShelfService.findAll(stanje));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookShelf> getByIf(@PathVariable Long id) {
        return ResponseEntity.ok(bookShelfService.findById(id));
    }

    @PostMapping
    public ResponseEntity<BookShelf> create(@RequestBody BookShelfRequest bookShelfRequest){
        BookShelf saved= bookShelfService.create(bookShelfRequest);

        return ResponseEntity
                .created(URI.create("api/shelf" + saved.getId()))
                .body(saved);
    }

    @PutMapping("{id}")
    public ResponseEntity<BookShelf> update(@PathVariable Long id,
                                        @RequestBody BookShelfRequest bookShelfRequest){
        return ResponseEntity.ok(bookShelfService.update(id, bookShelfRequest));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> update(@PathVariable Long id){
        bookShelfService.delete(id);
        return ResponseEntity.noContent().build();
    }
}