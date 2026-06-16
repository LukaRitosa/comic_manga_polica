package org.example.comic_manga_polica.service;

import org.example.comic_manga_polica.dto.BookShelfRequest;
import org.example.comic_manga_polica.entity.BookShelf;
import org.example.comic_manga_polica.entity.Status;

import java.util.List;

public interface BookShelfService {
    List<BookShelf> findAll(Status stanje);
    BookShelf findById(Long id);
    BookShelf create(BookShelfRequest req);
    BookShelf update(Long id, BookShelfRequest req);
    void delete(Long d);
}
