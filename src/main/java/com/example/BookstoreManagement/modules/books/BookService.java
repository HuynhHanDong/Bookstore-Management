package com.example.BookstoreManagement.modules.books;

import com.example.BookstoreManagement.database.entities.BookEntity;
import com.example.BookstoreManagement.database.repositories.BooksRepository;
import com.example.BookstoreManagement.modules.books.dto.BookRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    @Autowired
    private BooksRepository booksRepository;

    public void addBook(BookRequestDTO dto) {
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

    public void updateBook(Integer bookId, BookRequestDTO dto) {
        BookEntity book = getBook(bookId);
        book.setTitle(dto.getTitle());
        book.setDescription(dto.getDescription());
        book.setIsbn(dto.getIsbn());
        book.setAuthor(dto.getAuthor());
        booksRepository.save(book);
    }

    public void deleteBook(Integer bookId) {
        booksRepository.deleteById(bookId);
    }

    public List<BookEntity> listBook() {
        List<BookEntity> bookList = booksRepository.findAll();
        return bookList;
    }

    public List<BookEntity> findByTitle(String title) {
        List<BookEntity> bookList = booksRepository.findByTitleContainingIgnoreCase(title);
        return bookList;
    }

    public List<BookEntity> findByAuthor(String author) {
        List<BookEntity> bookList = booksRepository.findByAuthorContainingIgnoreCase(author);
        return bookList;
    }

    public BookEntity findByIsbn(String isbn) {
        Optional<BookEntity> book = booksRepository.findByIsbn(isbn);
        if (book.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found!");
        return book.get();
    }
}
