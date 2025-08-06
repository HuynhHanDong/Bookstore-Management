package com.example.BookstoreManagement.modules.categories;

import com.example.BookstoreManagement.database.entities.BookCategoryEntity;
import com.example.BookstoreManagement.database.entities.CategoryEntity;
import com.example.BookstoreManagement.modules.bookCategories.BookCategoryService;
import com.example.BookstoreManagement.modules.bookCategories.dto.BookCategoryResponseDTO;
import com.example.BookstoreManagement.modules.categories.dto.CategoryResponseDTO;
import com.example.BookstoreManagement.modules.categories.dto.CategoryRequestDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private BookCategoryService bookCategoryService;

    @PostMapping("/")
    public ResponseEntity addCategory(@RequestBody @Valid CategoryRequestDTO dto) {
        categoryService.addCategory(dto);
        return new ResponseEntity("Category added", HttpStatus.OK);
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity updateCategory(@PathVariable Integer categoryId, @RequestBody @Valid CategoryRequestDTO dto) {
        categoryService.updateCategory(categoryId, dto);
        return new ResponseEntity("Category updated", HttpStatus.OK);
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity deleteCategory(@PathVariable Integer categoryId) {
        categoryService.deleteCategory(categoryId);
        return new ResponseEntity("Category deleted", HttpStatus.OK);
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity getCategory(@PathVariable Integer categoryId) {
        CategoryEntity category = categoryService.getCategory(categoryId);
        return new ResponseEntity(CategoryResponseDTO.fromEntity(category), HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity listCategory() {
        List<CategoryEntity> categoryList = categoryService.listCategory();
        return new ResponseEntity(CategoryResponseDTO.fromEntities(categoryList), HttpStatus.OK);
    }

    @GetMapping("/{categoryId}/books")
    public ResponseEntity getBookList(@PathVariable Integer categoryId) {
        List<BookCategoryEntity> bookCategoryList = bookCategoryService.findByCategoryId(categoryId);
        return new ResponseEntity(BookCategoryResponseDTO.fromEntities(bookCategoryList), HttpStatus.OK);
    }
}
