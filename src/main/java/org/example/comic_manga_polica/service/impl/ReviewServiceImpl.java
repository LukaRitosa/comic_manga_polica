package org.example.comic_manga_polica.service.impl;

import org.example.comic_manga_polica.dto.ReviewRequest;
import org.example.comic_manga_polica.entity.BookShelf;
import org.example.comic_manga_polica.entity.Review;
import org.example.comic_manga_polica.exeption.NotFoundException;
import org.example.comic_manga_polica.repository.BookShelfRepository;
import org.example.comic_manga_polica.repository.ComicRepository;
import org.example.comic_manga_polica.repository.ReviewRepository;
import org.example.comic_manga_polica.service.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
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
    public List<Review> findAll(Integer zvjezdice) {
        if(zvjezdice!=null){
            return this.reviewRepository.findByZvjezdice(zvjezdice);
        }


        return this.reviewRepository.findAll();
    }

    @Override
    public Review findById(Long id) {
        return this.reviewRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Review not found " + id));
    }

    @Override
    public Review create(ReviewRequest req) {
        BookShelf bookShelf= bookShelfRepository.findById(req.getShelfId())
                .orElseThrow(() -> new NotFoundException("Shelf not found " + req.getShelfId()));

        Review review= new Review(null, req.getZvjezdice(), req.getKomentar(), bookShelf);

        return this.reviewRepository.save(review);
    }

    @Override
    public Review update(Long id, ReviewRequest req) {
        Review review= reviewRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Review not found " + id));

        BookShelf bookShelf= bookShelfRepository.findById(req.getShelfId())
                .orElseThrow(() -> new NotFoundException("Shelf not found " + req.getShelfId()));

        review.setZvjezdice(req.getZvjezdice());
        review.setKomentar(req.getKomentar());
        review.setBookShelf(bookShelf);

        return this.reviewRepository.save(review);
    }

    @Override
    public void delete(Long id) {
        if(!this.reviewRepository.existsById(id)){
            throw new NotFoundException("Review not found" + id);
        }

        this.reviewRepository.deleteById(id);
    }
}
