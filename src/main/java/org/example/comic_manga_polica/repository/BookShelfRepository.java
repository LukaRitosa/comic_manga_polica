package org.example.comic_manga_polica.repository;

import org.example.comic_manga_polica.entity.BookShelf;
import org.example.comic_manga_polica.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookShelfRepository extends JpaRepository<BookShelf, Long> {
    List<BookShelf> findByStanje(Status stanje);
}
