package org.example.comic_manga_polica.entity;

import jakarta.persistence.*;

@Entity
@Table(name="bookshelf")
public class BookShelf {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(
            nullable = false,
            length = 120
    )
    private Status stanje;

    @ManyToOne(optional=false, fetch = FetchType.EAGER)
    @JoinColumn(name = "comic_id", nullable=false)
    private Comic comic;


    protected BookShelf(){}

    public BookShelf(Long id, Status stanje, Comic comic) {
        setId(id);
        setStanje(stanje);
        setComic(comic);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Status getStanje() {
        return stanje;
    }

    public void setStanje(Status stanje) {
        this.stanje = stanje;
    }

    public Comic getComic() {
        return comic;
    }

    public void setComic(Comic comic) {
        this.comic = comic;
    }
}
