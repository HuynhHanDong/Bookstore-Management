package com.example.BookstoreManagement.modules.reviews;

import com.example.BookstoreManagement.database.entities.BookEntity;
import com.example.BookstoreManagement.database.entities.ReviewEntity;
import com.example.BookstoreManagement.database.entities.UserEntity;
import com.example.BookstoreManagement.database.repositories.BooksRepository;
import com.example.BookstoreManagement.database.repositories.ReviewsRepository;
import com.example.BookstoreManagement.database.repositories.UsersRepository;
import com.example.BookstoreManagement.modules.reviews.dto.ReviewRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewService {
    @Autowired
    private ReviewsRepository reviewsRepository;

    @Autowired
    private BooksRepository booksRepository;

    @Autowired
    private UsersRepository usersRepository;

    public void addReview(ReviewRequestDTO dto) {
        Optional<BookEntity> book = booksRepository.findByBookId(dto.getBookId());
        if (book.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found");

        Optional<UserEntity> user = usersRepository.findByUserId(dto.getUserId());
        if (user.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");

        ReviewEntity review = new ReviewEntity(dto.getRate(), book.get(), user.get(), dto.getContent());
        reviewsRepository.save(review);
    }

    public ReviewEntity getReview(Integer reviewId) {
        Optional<ReviewEntity> review = reviewsRepository.findByReviewId(reviewId);
        if (review.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Review not found");
        return review.get();
    }

    public void deleteReview(Integer reviewId) {
        reviewsRepository.deleteById(reviewId);
    }

    public List<ReviewEntity> listReview() {
        List<ReviewEntity> reviewList = reviewsRepository.findAll();
        return reviewList;
    }

    public List<ReviewEntity> findByRate(Integer rate) {
        List<ReviewEntity> reviewList = reviewsRepository.findByRate(rate);
        return reviewList;
    }

    public List<ReviewEntity> findByBookId(Integer bookId) {
        Optional<BookEntity> book = booksRepository.findByBookId(bookId);
        if (book.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found");
        List<ReviewEntity> reviewList = reviewsRepository.findByBookId(book.get());
        return reviewList;
    }
}
