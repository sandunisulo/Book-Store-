package com.backend.demo.service;

import com.backend.demo.entity.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getAllCategory();
    void save(Category category);
    Category getCategoryByName(String name);
    public long generateUniqueReferenceNumber();
}
