package org.example.comic_manga_polica.entity;

import jakarta.persistence.*;


public class BookShelf {
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
    private String name;

    @Column(
            nullable = false,
            unique = true,
            length = 120
    )
    private String autor;

    @Column(
            nullable = false,
            length = 120
    )
    private String slika;






}
