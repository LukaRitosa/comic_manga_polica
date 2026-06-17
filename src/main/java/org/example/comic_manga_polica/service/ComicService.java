package org.example.comic_manga_polica.service;


import org.example.comic_manga_polica.dto.ComicRequest;
import org.example.comic_manga_polica.entity.Comic;
import org.example.comic_manga_polica.entity.Tip;

import java.util.List;

public interface ComicService {
    List<Comic> findAll(String naziv, Integer year);
    Comic findById(Long Id);
    Comic create(ComicRequest req);
    Comic update(Long id, ComicRequest req);
    void delete(Long id);
    Comic findByShelfId(Long shelfId);
    Comic createComicFromApi(String naziv, Tip tip);
}
