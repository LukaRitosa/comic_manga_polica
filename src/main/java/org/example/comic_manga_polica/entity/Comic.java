package org.example.comic_manga_polica.entity;

import jakarta.persistence.*;

@Entity
@Table(name="comic")
public class Comic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 120)
    private String naziv;

    @Column(nullable = false, length = 120)
    private String autor;

    @Column(nullable = false, length = 1000)
    private String slika;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 120)
    private Tip tip;

    @Column(nullable = false)
    private Integer releaseYear;


    protected Comic(){}

    public Comic(Long id, String naziv, String autor, String slika, Tip tip, Integer releaseYear) {
        setId(id);
        setNaziv(naziv);
        setAutor(autor);
        setSlika(slika);
        setTip(tip);
        setReleaseYear(releaseYear);
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
