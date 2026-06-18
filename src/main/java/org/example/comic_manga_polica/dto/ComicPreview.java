package org.example.comic_manga_polica.dto;

import org.example.comic_manga_polica.entity.Tip;

public record ComicPreview (
        Tip tip,
        Long externalId,
        String naziv,
        String slika,
        Integer releaseYear) {}
