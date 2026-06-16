package org.example.comic_manga_polica.service;

import org.example.comic_manga_polica.dto.ReviewRequest;
import org.example.comic_manga_polica.entity.Review;

import java.util.List;

public interface ReviewService {
    List<Review> findAll(Integer zvjezdice);
    Review findById(Long Id);
    Review create(ReviewRequest req);
    Review update(Long id, ReviewRequest req);
    void delete(Long id);
}
