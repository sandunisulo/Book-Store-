package com.backend.demo.serviceImpl;

import com.backend.demo.entity.Book;
import com.backend.demo.entity.Category;
import com.backend.demo.repository.CategoryRepository;
import com.backend.demo.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    //get all categories
    @Override
    public List<Category> getAllCategory(){
        return categoryRepository.findAll();
    }

    //find by name
    @Override
    public Category getCategoryByName(String name){
        return categoryRepository.findByName(name);
    }

    @Override
    public void save(Category category){
        System.out.println("save category out");
        categoryRepository.save(category);
    }
    @Override
    public long generateUniqueReferenceNumber() {
        UUID uuid = UUID.randomUUID();
        return Math.abs(uuid.getMostSignificantBits());
    }
}
