package org.example.comic_manga_polica.repository;

import org.example.comic_manga_polica.entity.Comic;
import org.example.comic_manga_polica.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository  extends JpaRepository<Review, Long> {
    Optional<Review> findByBookShelfId(Long bookShelfId);
}
