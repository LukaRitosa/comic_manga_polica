package org.example.comic_manga_polica.dto;

import jakarta.validation.constraints.*;
import org.example.comic_manga_polica.entity.Status;

public class BookShelfRequest {
    @NotNull(message = "status required")
    private Status stanje;

    @NotNull(message = "comic is reqired")
    private Long comicId;

    public Status getStanje() {
        return stanje;
    }

    public void setStanje(Status stanje) {
        this.stanje = stanje;
    }

    public Long getComicId() {
        return comicId;
    }

    public void setComicId(Long comicId) {
        this.comicId = comicId;
    }
}
