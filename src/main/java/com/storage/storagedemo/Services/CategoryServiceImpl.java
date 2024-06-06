package com.storage.storagedemo.Services;


import com.storage.storagedemo.Exceptions.CustomExceptions.ResourceNotFoundException;
import com.storage.storagedemo.Models.Category;
import com.storage.storagedemo.Repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService{

    private final CategoryRepository categoryRepository;

    @Override
    public Category getCategoryById(Integer id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Category with id: " + id + " not found")
                );

        return category;
    }
}
