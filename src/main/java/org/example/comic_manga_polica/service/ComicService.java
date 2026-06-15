package org.example.comic_manga_polica.service;


import org.example.comic_manga_polica.dto.ComicRequest;
import org.example.comic_manga_polica.entity.Comic;

import java.util.List;

public interface ComicService {
    List<Comic> FindAll();
    Comic findById(Long Id);
    Comic create(ComicRequest req);
    Comic update(ComicRequest req);
    Void delete(Long comicId);
    Comic findByShelfId(Long shelfId);
}
