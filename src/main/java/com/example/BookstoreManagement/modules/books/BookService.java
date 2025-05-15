package com.example.BookstoreManagement.modules.books;

import com.example.BookstoreManagement.database.entities.BookEntity;
import com.example.BookstoreManagement.database.repositories.BooksRepository;
import com.example.BookstoreManagement.modules.books.dto.CreateBookDTO;
import com.example.BookstoreManagement.modules.books.dto.UpdateBookDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    @Autowired
    private BooksRepository booksRepository;

    public void addBook(CreateBookDTO dto) {
        Optional<BookEntity> checkIsbn = booksRepository.findByIsbn(dto.getIsbn());
        if (checkIsbn.isPresent()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ISBN already existed");
        BookEntity book = new BookEntity(dto);
        booksRepository.save(book);
    }

    public BookEntity getBook(Integer bookId) {
        Optional<BookEntity> book = booksRepository.findByBookId(bookId);
        if (book.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found!");
        return book.get();
    }

    public void updateBook(Integer bookId, UpdateBookDTO dto) {
        BookEntity bookEntity = getBook(bookId);
        bookEntity.setTitle(dto.getTitle());
        bookEntity.setDescription(dto.getDescription());
        bookEntity.setIsbn(dto.getIsbn());
        bookEntity.setAuthor(dto.getAuthor());
        bookEntity.setUpdatedAt(Instant.now());
        booksRepository.save(bookEntity);
    }

    public void deleteBook(Integer bookId) {
        booksRepository.deleteById(bookId);
    }

    public List<BookEntity> listBook() {
        List<BookEntity> bookList = booksRepository.findAll();
        return bookList;
    }

    public List<BookEntity> findByTitle(String title) {
        List<BookEntity> bookList = booksRepository.findByTitle(title);
        return bookList;
    }

    public List<BookEntity> findByAuthor(String author) {
        List<BookEntity> bookList = booksRepository.findByAuthor(author);
        return bookList;
    }

    public BookEntity findByIsbn(String isbn) {
        Optional<BookEntity> book = booksRepository.findByIsbn(isbn);
        if (book.isEmpty()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Book not found!");
        return book.get();
    }
}
