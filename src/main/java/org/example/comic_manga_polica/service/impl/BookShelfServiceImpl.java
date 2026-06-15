package org.example.comic_manga_polica.service.impl;

import org.example.comic_manga_polica.dto.BookShelfRequest;
import org.example.comic_manga_polica.entity.BookShelf;
import org.example.comic_manga_polica.entity.Status;
import org.example.comic_manga_polica.exeption.NotFoundException;
import org.example.comic_manga_polica.repository.BookShelfRepository;
import org.example.comic_manga_polica.repository.ComicRepository;
import org.example.comic_manga_polica.service.BookShelfService;

import java.util.List;

public class BookShelfServiceImpl implements BookShelfService {
    BookShelfRepository bookShelfRepository;
    ComicRepository comicRepository;

    public BookShelfServiceImpl(ComicRepository comicRepository, BookShelfRepository bookShelfRepository) {
        this.comicRepository = comicRepository;
        this.bookShelfRepository = bookShelfRepository;
    }

    @Override
    public List<BookShelf> FindAll(Status stanje) {
        if(stanje!=null){
            return this.bookShelfRepository.findByStanje(stanje);
        }
        return this.bookShelfRepository.findAll();
    }

    @Override
    public BookShelf findById(Long id) {
        return this.bookShelfRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("Shelf ne postoji " + id));
    }

    @Override
    public BookShelf create(BookShelfRequest req) {
        return null;
    }

    @Override
    public BookShelf update(BookShelfRequest req) {
        return null;
    }

    @Override
    public Void delete(Long comicId) {
        return null;
    }
}
