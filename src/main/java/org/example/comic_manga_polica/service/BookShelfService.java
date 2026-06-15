package org.example.comic_manga_polica.service;

import org.example.comic_manga_polica.dto.BookShelfRequest;
import org.example.comic_manga_polica.entity.BookShelf;

import java.util.List;

public interface BookShelfService {
    List<BookShelf> FindAll();
    BookShelf findById(Long Id);
    BookShelf create(BookShelfRequest req);
    BookShelf update(BookShelfRequest req);
    Void delete(Long comicId);
}
