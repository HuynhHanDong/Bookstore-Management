package com.example.BookstoreManagement.modules.bookCategories;

import com.example.BookstoreManagement.database.entities.BookCategoryEntity;
import com.example.BookstoreManagement.modules.bookCategories.dto.BookCategoryRequestDTO;
import com.example.BookstoreManagement.modules.bookCategories.dto.BookCategoryResponseDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book-categories")
public class BookCategoryController {
    @Autowired
    private BookCategoryService bookCategoryService;

    @PostMapping("/")
    public ResponseEntity addBookCategory(@RequestBody @Valid BookCategoryRequestDTO dto) {
        bookCategoryService.addBookCategory(dto);
        return new ResponseEntity("Book category added", HttpStatus.OK);
    }

    @DeleteMapping("/{bookCategoryId}")
    public ResponseEntity deleteBookCategory(@PathVariable Integer bookCategoryId) {
        bookCategoryService.deleteBookCategory(bookCategoryId);
        return new ResponseEntity("Book category deleted", HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity listBookCategory() {
        List<BookCategoryEntity> entities = bookCategoryService.listBookCategory();
        return new ResponseEntity(BookCategoryResponseDTO.fromEntities(entities), HttpStatus.OK);
    }

    @GetMapping("/find-by-category/{name}")
    public ResponseEntity findByCategoryName(@PathVariable String name) {
        List<BookCategoryEntity> entities = bookCategoryService.findBookByCategory(name);
        return new ResponseEntity(BookCategoryResponseDTO.fromEntities(entities), HttpStatus.OK);
    }

    @GetMapping("/find-by-book/{bookId}")
    public ResponseEntity findByBookId(@PathVariable Integer bookId) {
        List<BookCategoryEntity> entities = bookCategoryService.findCategoryByBook(bookId);
        return new ResponseEntity(BookCategoryResponseDTO.fromEntities(entities), HttpStatus.OK);
    }
}
