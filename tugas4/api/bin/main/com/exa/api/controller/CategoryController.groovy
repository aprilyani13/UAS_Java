package com.exa.api.controller

import com.exa.api.entity.Category
import com.exa.api.dto.CategoryDTO
import com.exa.api.service.CategoryService
import org.springframework.web.bind.annotation.*
import org.springframework.beans.factory.annotation.Autowired

@RestController
@RequestMapping('/categories')
class CategoryController {
    @Autowired
    private final CategoryService categoryService
    @GetMapping('')
    List<Category> findAll(
        @RequestParam(value="include",required = false) String include
    ) {
        categoryService.findAll(include)
    }

    @GetMapping('{id}')
    CategoryDTO findById(
        @PathVariable('id') int id,
        @RequestParam(value="include",required = false) String include
    ) {
        categoryService.findById(id,include)
    }

    @PostMapping()
    Category save(@RequestBody Category category) {
        categoryService.save(category)
    }

    @PutMapping('{id}')
    Category update(@RequestBody Category category, @PathVariable('id') int id) {
        categoryService.update(category, id)
    }

    @DeleteMapping('{id}')
    Category delete(@PathVariable('id') int id) {
        categoryService.delete(id)
    }
}