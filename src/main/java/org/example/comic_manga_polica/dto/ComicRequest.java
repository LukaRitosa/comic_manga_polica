package org.example.comic_manga_polica.dto;

import jakarta.validation.constraints.*;
import org.example.comic_manga_polica.entity.Tip;

public class ComicRequest {

    @NotBlank(message = "naziv is required")
    private String naziv;

    @NotBlank(message = "autor is required")
    private String autor;

    @NotBlank(message = "slika is required")
    private String slika;

    @NotNull(message = "tip is required")
    private Tip tip;

    @NotNull(message = "releaseYear is required")
    @Min(value = 1837, message = "prvi strp je izašao 1837")
    @Max(value = 2026, message = "tekuća godina je 2026")
    private Integer releaseYear;

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getSlika() {
        return slika;
    }

    public void setSlika(String slika) {
        this.slika = slika;
    }

    public Tip getTip() {
        return tip;
    }

    public void setTip(Tip tip) {
        this.tip = tip;
    }

    public Integer getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(Integer releaseYear) {
        this.releaseYear = releaseYear;
    }
}
