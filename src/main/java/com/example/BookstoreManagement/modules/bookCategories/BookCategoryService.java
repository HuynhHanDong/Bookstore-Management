package com.example.BookstoreManagement.modules.bookCategories;

import com.example.BookstoreManagement.database.entities.BookCategoryEntity;
import com.example.BookstoreManagement.database.entities.BookEntity;
import com.example.BookstoreManagement.database.entities.CategoryEntity;
import com.example.BookstoreManagement.database.repositories.BookCategoriesRepository;
import com.example.BookstoreManagement.database.repositories.BooksRepository;
import com.example.BookstoreManagement.database.repositories.CategoriesRepository;
import com.example.BookstoreManagement.modules.bookCategories.dto.BookCategoryRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class BookCategoryService {
    @Autowired
    private BookCategoriesRepository bookCategoriesRepository;

    @Autowired
    private BooksRepository booksRepository;

    @Autowired
    private CategoriesRepository categoriesRepository;

    public void addBookCategory(BookCategoryRequestDTO dto) {
        Optional<BookEntity> book = booksRepository.findByBookId(dto.getBookId());
        if (book.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found");

        Optional<CategoryEntity> category = categoriesRepository.findByCategoryId(dto.getCategoryId());
        if (category.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found");

        BookCategoryEntity bookCategory = new BookCategoryEntity(book.get(), category.get());
        bookCategoriesRepository.save(bookCategory);
    }

    public void deleteBookCategory(Integer bookCategoryId) {
        bookCategoriesRepository.deleteById(bookCategoryId);
    }

    public List<BookCategoryEntity> listBookCategory() {
        List<BookCategoryEntity> bookCategorylist = bookCategoriesRepository.findAll();
        return bookCategorylist;
    }

    public List<BookCategoryEntity> findByCategoryId(Integer categoryId) {
        Optional<CategoryEntity> category = categoriesRepository.findByCategoryId(categoryId);
        if (category.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found");
        List<BookCategoryEntity> bookCategorylist = bookCategoriesRepository.findByCategoryId(category.get());
        return bookCategorylist;
    }

    public List<BookCategoryEntity> findByCategoryName(String name) {
        Optional<CategoryEntity> category = categoriesRepository.findByName(name);
        if (category.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found");
        List<BookCategoryEntity> bookCategorylist = bookCategoriesRepository.findByCategoryId(category.get());
        return bookCategorylist;
    }

    public List<BookCategoryEntity> findByBookId(Integer bookId) {
        Optional<BookEntity> book = booksRepository.findByBookId(bookId);
        if (book.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found");
        List<BookCategoryEntity> bookCategorylist = bookCategoriesRepository.findByBookId(book.get());
        return bookCategorylist;
    }
}
