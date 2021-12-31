package com.exa.api.service

import com.exa.api.dto.CategoryDTO
import com.exa.api.entity.Category

interface CategoryService {
    List<Category> findAll(String include)
    CategoryDTO findById(int id, String include)
    Category save(Category category)
    Category update(Category category, int id)
    Category delete(int id)
}