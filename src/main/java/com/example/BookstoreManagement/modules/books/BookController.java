package com.example.BookstoreManagement.modules.books;

import com.example.BookstoreManagement.database.entities.BookCategoryEntity;
import com.example.BookstoreManagement.database.entities.BookEntity;
import com.example.BookstoreManagement.modules.bookCategories.BookCategoryService;
import com.example.BookstoreManagement.modules.bookCategories.dto.BookCategoryResponseDTO;
import com.example.BookstoreManagement.modules.books.dto.BookResponeDTO;
import com.example.BookstoreManagement.modules.books.dto.BookRequestDTO;
import com.example.BookstoreManagement.utils.ValidIsbn;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {
    @Autowired
    private BookService bookService;

    @Autowired
    private BookCategoryService bookCategoryService;

    @PostMapping("/")
    public ResponseEntity addBook(@RequestBody @Valid BookRequestDTO dto) {
        bookService.addBook(dto);
        return new ResponseEntity("Book added", HttpStatus.OK);
    }

    @PutMapping("/{bookId}")
    public ResponseEntity updateBook(@PathVariable Integer bookId, @RequestBody @Valid BookRequestDTO dto) {
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

    @GetMapping("/search")
    public ResponseEntity findByTitle(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String author,
            @RequestParam(required = false) String isbn,
            @RequestParam(required = false) String category
    ) {
        if (title != null) {
            List<BookEntity> bookList = bookService.findByTitle(title);
            return new ResponseEntity(BookResponeDTO.fromEntities(bookList), HttpStatus.OK);
        } else if (author != null) {
            List<BookEntity> bookList = bookService.findByAuthor(author);
            return new ResponseEntity(BookResponeDTO.fromEntities(bookList), HttpStatus.OK);
        } else if (isbn != null) {
            BookEntity book = bookService.findByIsbn(isbn);
            return new ResponseEntity(BookResponeDTO.fromEntity(book), HttpStatus.OK);
        } else {
            List<BookCategoryEntity> bookCategoryList = bookCategoryService.findByCategoryName(category);
            return new ResponseEntity(BookCategoryResponseDTO.fromEntities(bookCategoryList), HttpStatus.OK);
        }
    }

    @GetMapping("/{bookId}/categories")
    public ResponseEntity getCategoryList(@PathVariable Integer bookId) {
        List<BookCategoryEntity> bookCategoryList = bookCategoryService.findByBookId(bookId);
        return new ResponseEntity(BookCategoryResponseDTO.fromEntities(bookCategoryList), HttpStatus.OK);
    }
}
