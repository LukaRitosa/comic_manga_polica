package org.example.comic_manga_polica.controller.rest;

import org.example.comic_manga_polica.dto.ComicRequest;
import org.example.comic_manga_polica.entity.Comic;
import org.example.comic_manga_polica.service.ComicService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api/comics")
public class ComicController {
    private ComicService comicService;

    public ComicController(ComicService comicService) {
        this.comicService = comicService;
    }

    @GetMapping
    public ResponseEntity<List<Comic>> getAll(@RequestParam(required = false) String naslov, @RequestParam(required = false) Integer year){

        return ResponseEntity.ok(comicService.findAll(naslov, year));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Comic> getById(@PathVariable Long id){
        return ResponseEntity.ok(comicService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Comic> create(@RequestBody ComicRequest comicRequest){
        Comic saved= comicService.create(comicRequest);

        return ResponseEntity
                .created(URI.create("api/comics/" + saved.getId()))
                .body(saved);
    }

    @PutMapping("{id}")
    public ResponseEntity<Comic> update(@PathVariable Long id,
                                        @RequestBody ComicRequest comicRequest){
        return ResponseEntity.ok(comicService.update(id, comicRequest));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> update(@PathVariable Long id){
        comicService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/shelf/{id}")
    public ResponseEntity<Comic> getByShelfId(@PathVariable Long id){
        return ResponseEntity.ok(comicService.findByShelfId(id));
    }
}
