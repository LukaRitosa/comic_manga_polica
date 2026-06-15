package org.example.comic_manga_polica.repository;

import org.example.comic_manga_polica.entity.Comic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ComicRepository extends JpaRepository<Comic, Long> {

    Optional<Comic> findByNaziv(String naziv);

    boolean existsByNaziv(String naziv);
    
    List<Comic> findByZvjezdice(Integer zvjezdice);

    List<Comic> findByReleaseYear(Integer releaseYear);
}
