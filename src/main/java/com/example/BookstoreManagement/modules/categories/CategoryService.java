package com.example.BookstoreManagement.modules.categories;

import com.example.BookstoreManagement.database.entities.CategoryEntity;
import com.example.BookstoreManagement.database.repositories.CategoriesRepository;
import com.example.BookstoreManagement.modules.categories.dto.CategoryRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    private CategoriesRepository categoriesRepository;

    public void addCategory(CategoryRequestDTO dto) {
        Optional<CategoryEntity> checkName = categoriesRepository.findByName(dto.getName());
        if (checkName.isPresent()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Category name already existed");
        CategoryEntity category = new CategoryEntity(dto);
        categoriesRepository.save(category);
    }

    public CategoryEntity getCategory(Integer categoryId) {
        Optional<CategoryEntity> category = categoriesRepository.findByCategoryId(categoryId);
        if (category.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found");
        return category.get();
    }

    public void updateCategory(Integer categoryId, CategoryRequestDTO dto) {
        CategoryEntity category = getCategory(categoryId);
        category.setName(dto.getName());
        category.setDescription(dto.getDescription());
        categoriesRepository.save(category);
    }

    public void deleteCategory(Integer categoryId) {
        categoriesRepository.deleteById(categoryId);
    }

    public List<CategoryEntity> listCategory() {
        List<CategoryEntity> categoryList = categoriesRepository.findAll();
        return categoryList;
    }

    public CategoryEntity findByName(String name) {
        Optional<CategoryEntity> category = categoriesRepository.findByName(name);
        if (category.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found");
        return category.get();
    }
}
