package org.example.comic_manga_polica.entity;

import jakarta.persistence.*;

@Entity
@Table(name="comic")
public class Comic {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;

    @Column(
            nullable = false,
            unique = true,
            length = 120
    )
    private String naziv;

    @Column(
            nullable = false,
            length = 120
    )
    private String autor;

    @Column(
            nullable = false,
            length = 1000
    )
    private String slika;

    @Enumerated(EnumType.STRING)
    @Column(
            nullable = false,
            length = 120
    )
    private Tip tip;

    public enum Tip {
        COMIC,
        MANGA
    }

    protected Comic(){}

    public Comic(Long id, String naziv, String autor, String slika) {
        setId(id);
        setNaziv(naziv);
        setAutor(autor);
        setSlika(slika);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
}
