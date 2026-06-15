package org.example.comic_manga_polica.service.impl;

import org.example.comic_manga_polica.dto.ComicRequest;
import org.example.comic_manga_polica.entity.Comic;
import org.example.comic_manga_polica.exeption.NotFoundException;
import org.example.comic_manga_polica.repository.BookShelfRepository;
import org.example.comic_manga_polica.repository.ComicRepository;
import org.example.comic_manga_polica.service.BookShelfService;
import org.example.comic_manga_polica.service.ComicService;

import java.util.List;

public class ComicServiceImpl implements ComicService {
    private ComicRepository comicRepository;
    private BookShelfRepository bookShelfRepository;

    public ComicServiceImpl(ComicRepository comicRepository, BookShelfRepository bookShelfRepository) {
        this.comicRepository = comicRepository;
        this.bookShelfRepository = bookShelfRepository;
    }

    @Override
    public List<Comic> FindAll(String naziv, Integer year) {
        if (naziv!=null && !naziv.isBlank()){
            return this.comicRepository.findByNazivContainingIgnoreCase(naziv);
        }
        if(year!=null){
            return this.comicRepository.findByReleaseYear(year);
        }
        return this.comicRepository.findAll();
    }

    @Override
    public Comic findById(Long id) {
        return this.comicRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Nema tog stripa " + id));
    }

    @Override
    public Comic create(ComicRequest req) {
        return null;
    }

    @Override
    public Comic update(ComicRequest req) {
        return null;
    }

    @Override
    public Void delete(Long comicId) {
        return null;
    }

    @Override
    public Comic findByShelfId(Long shelfId) {
        return null;
    }
}
