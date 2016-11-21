package com.dawud.events.service;

import com.dawud.events.domain.Category;
import com.dawud.events.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 * Service class for handling Category request.
 */
@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getCategories() throws IOException {
        List<Category> categories = categoryRepository.getCategories();
        return categories;
    }

}
