package org.example.comic_manga_polica.service.impl;

import org.example.comic_manga_polica.dto.ReviewRequest;
import org.example.comic_manga_polica.entity.Review;
import org.example.comic_manga_polica.exeption.NotFoundException;
import org.example.comic_manga_polica.repository.BookShelfRepository;
import org.example.comic_manga_polica.repository.ComicRepository;
import org.example.comic_manga_polica.repository.ReviewRepository;
import org.example.comic_manga_polica.service.ReviewService;

import java.util.List;

public class ReviewServiceImpl implements ReviewService {
    ReviewRepository reviewRepository;
    BookShelfRepository bookShelfRepository;
    ComicRepository comicRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository, BookShelfRepository bookShelfRepository, ComicRepository comicRepository) {
        this.reviewRepository = reviewRepository;
        this.bookShelfRepository = bookShelfRepository;
        this.comicRepository = comicRepository;
    }

    @Override
    public List<Review> FindAll(Long comicMangaId, Integer zvjezdice) {
        if(zvjezdice!=null){
            return this.reviewRepository.findByZvjezdice(zvjezdice);
        }

//        if(comicMangaId!=null){
//            Review r= reviewRepository
//            return
//        }

        return this.reviewRepository.findAll();
    }

    @Override
    public Review findById(Long id) {
        return this.reviewRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Review not found" + id));
    }

    @Override
    public Review create(ReviewRequest req) {
        return null;
    }

    @Override
    public Review update(ReviewRequest req) {
        return null;
    }

    @Override
    public Void delete(Long reviewId) {
        return null;
    }
}
