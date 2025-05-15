package com.example.BookstoreManagement.modules.books;

import com.example.BookstoreManagement.database.entities.BookEntity;
import com.example.BookstoreManagement.modules.books.dto.BookResponeDTO;
import com.example.BookstoreManagement.modules.books.dto.CreateBookDTO;
import com.example.BookstoreManagement.modules.books.dto.UpdateBookDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/books")
public class BookController {
    @Autowired
    private BookService bookService;

    @PostMapping("/")
    public ResponseEntity addBook(@RequestBody @Valid CreateBookDTO dto) {
        bookService.addBook(dto);
        return new ResponseEntity("Book added", HttpStatus.OK);
    }

    @PutMapping("/{bookId}")
    public ResponseEntity updateBook(@PathVariable Integer bookId, @RequestBody @Valid UpdateBookDTO dto) {
        bookService.updateBook(bookId, dto);
        return new ResponseEntity("Book updated", HttpStatus.OK);
    }

    @DeleteMapping("/{bookId}")
    public ResponseEntity deleteBook(@PathVariable Integer bookId) {
        bookService.deleteBook(bookId);
        return new ResponseEntity("Book deleted", HttpStatus.OK);
    }

    @GetMapping("/{bookId}")
    public ResponseEntity getBook(@PathVariable @Valid Integer bookId) {
        BookEntity book = bookService.getBook(bookId);
        return new ResponseEntity(BookResponeDTO.fromEntity(book), HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity listBook() {
        List<BookEntity> bookList = bookService.listBook();
        return new ResponseEntity(BookResponeDTO.fromEntities(bookList), HttpStatus.OK);
    }

    @GetMapping("/title/{title}")
    public ResponseEntity findByTitle(@PathVariable String title) {
        List<BookEntity> bookList = bookService.findByTitle(title);
        return new ResponseEntity(BookResponeDTO.fromEntities(bookList), HttpStatus.OK);
    }

    @GetMapping("/author/{author}")
    public ResponseEntity findByAuthor(@PathVariable String author) {
        List<BookEntity> bookList = bookService.findByAuthor(author);
        return new ResponseEntity(BookResponeDTO.fromEntities(bookList), HttpStatus.OK);
    }

    @GetMapping("/isbn/{isbn}")
    public ResponseEntity findByIsbn(@PathVariable String isbn) {
        BookEntity book = bookService.findByIsbn(isbn);
        return new ResponseEntity(BookResponeDTO.fromEntity(book), HttpStatus.OK);
    }
}
