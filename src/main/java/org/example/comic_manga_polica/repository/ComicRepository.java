package org.example.comic_manga_polica.repository;

import org.example.comic_manga_polica.entity.Comic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ComicRepository extends JpaRepository<Comic, Long> {

    List<Comic> findByNazivContainingIgnoreCase(String naziv);

    boolean existsByNaziv(String naziv);

    List<Comic> findByNazivContainingIgnoreCaseAndReleaseYear(String naziv, Integer releaseYear);

    List<Comic> findByReleaseYear(Integer releaseYear);

    Optional<Comic> findByNaziv(String naziv);

    @Query("SELECT c FROM Comic c WHERE c.id NOT IN (SELECT b.comic.id FROM BookShelf b)")
    List<Comic> findComicsNotOnShelf();
}
