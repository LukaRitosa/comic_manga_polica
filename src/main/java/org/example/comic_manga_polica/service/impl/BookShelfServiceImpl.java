package org.example.comic_manga_polica.service.impl;

import org.example.comic_manga_polica.dto.BookShelfRequest;
import org.example.comic_manga_polica.entity.BookShelf;
import org.example.comic_manga_polica.entity.Comic;
import org.example.comic_manga_polica.entity.Status;
import org.example.comic_manga_polica.exeption.NotFoundException;
import org.example.comic_manga_polica.repository.BookShelfRepository;
import org.example.comic_manga_polica.repository.ComicRepository;
import org.example.comic_manga_polica.service.BookShelfService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookShelfServiceImpl implements BookShelfService {
    BookShelfRepository bookShelfRepository;
    ComicRepository comicRepository;

    public BookShelfServiceImpl(ComicRepository comicRepository, BookShelfRepository bookShelfRepository) {
        this.comicRepository = comicRepository;
        this.bookShelfRepository = bookShelfRepository;
    }

    @Override
    public List<BookShelf> findAll(Status stanje) {
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
        Comic comic= comicRepository.findById(req.getComicId())
                .orElseThrow(() -> new NotFoundException("Comic not found" + req.getComicId()));

        BookShelf bookShelf= new BookShelf(null, req.getStanje(), comic);

        return this.bookShelfRepository.save(bookShelf);
    }

    @Override
    public BookShelf update(Long id, BookShelfRequest req) {
        Comic comic= comicRepository.findById(req.getComicId())
                .orElseThrow(() -> new NotFoundException("Comic not found" + req.getComicId()));

        BookShelf bookShelf= this.bookShelfRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Bookshelf not found" + req.getComicId()));;


        bookShelf.setStanje(req.getStanje());
        bookShelf.setComic(comic);

        return this.bookShelfRepository.save(bookShelf);
    }

    @Override
    public void delete(Long id) {
        if(!this.bookShelfRepository.existsById(id)){
            throw new NotFoundException("BookShelf not found " + id);
        }

        this.bookShelfRepository.deleteById(id);
    }
}
