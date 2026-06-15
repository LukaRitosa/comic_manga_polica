package org.example.comic_manga_polica.entity;

import jakarta.persistence.*;

@Entity
@Table(name="review")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer zvjezdice;


    @Column(length = 500)
    private String komentar;

    @OneToOne(optional=false, fetch = FetchType.EAGER)
    @JoinColumn(name = "shelf_id", nullable=false, unique = true)
    private BookShelf bookShelf;

    protected Review() {}

    public Review(Long id, Integer zvjezdice, String komentar, BookShelf bookShelf) {
        setId(id);
        setZvjezdice(zvjezdice);
        setKomentar(komentar);
        setBookShelf(bookShelf);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getZvjezdice() {
        return zvjezdice;
    }

    public void setZvjezdice(Integer zvjezdice) {
        this.zvjezdice = zvjezdice;
    }

    public String getKomentar() {
        return komentar;
    }

    public void setKomentar(String komentar) {
        this.komentar = komentar;
    }

    public BookShelf getBookShelf() {
        return bookShelf;
    }

    public void setBookShelf(BookShelf bookShelf) {
        this.bookShelf = bookShelf;
    }
}
