package com.exa.api.service.impl

import com.exa.api.entity.Category
import com.exa.api.entity.CategoryBook
import com.exa.api.entity.Books
import com.exa.api.dto.CategoryDTO
import com.exa.api.dto.BooksDTO
import com.exa.api.repository.CategoryRepository
import com.exa.api.repository.CategoryBooksRepository
import com.exa.api.service.CategoryService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CategoryServiceImpl implements CategoryService {
    @Autowired
    private final CategoryRepository CategoryRepository

    @Autowired
    private CategoryBooksRepository categoryBooksRepository;

    @Override
    List<Category> findAll(String include) {
        List<Category> categories;
        categories = CategoryRepository.findAll();
        List<Category> categoriesList = new ArrayList<>();

        if (categories != null) {
            for (Category category : categories) {
                categoryDTO categoryDTO = new CategoryDTO();
                categoryDTO.setId(category.getId());
                categoryDTO.setName(category.getName());

                List<CategoryBook> categoryBooks = null;
                if (include == "books") {
                    categoryBooks = categoryBooksRepository.findAllByBookId(category.getId());
                }
                List<BooksDTO> bookDTOList = new ArrayList<>();
                if (categoryBooks != null) {
                    for (Books categoryBook : categoryBooks) {
                        BooksDTO bookDTO = new BooksDTO();
                        bookDTO.setId(categoryBook.getId());
                        bookDTO.setName(categoryBook.getName());
                        bookDTO.setIsbn(categoryBook.getIsbn());
                        bookDTO.setCategory_id(categoryBook.getCategory_id());

                        bookDTOList.add(bookDTO);
                    }

                    categoryDTO.setBooks(bookDTOList);
                }

                categoriesList.add(categoryDTO);
            }
        }
        return categoriesList;
    }

    @Override
    CategoryDTO findById(int id, String include) {
        Category category;
        List<CategoryBook> categoryBooks = null;

        category = CategoryRepository.findById(id);

        if (include == "books") {
            categoryBooks = categoryBooksRepository.findAllByBookId(id);
        }

        CategoryDTO categoryDTO = new CategoryDTO();

        categoryDTO.setId(category.getId());
        categoryDTO.setName(category.getName());

        List<BooksDTO> bookDTOList = new ArrayList<>();
        if (categoryBooks != null) {
            for (Books categoryBook : categoryBooks) {
                BooksDTO bookDTO = new BookDTO();
                bookDTO.setId(categoryBook.getId());
                bookDTO.setName(categoryBook.getName());
                bookDTO.setIsbn(categoryBook.getIsbn());
                bookDTO.setCategory_id(categoryBook.getCategory_id());

                bookDTOList.add(bookDTO);
            }

            categoryDTO.setBooks(bookDTOList);
        }
        return categoryDTO;
    }

    @Override
    Category save(Category category) {
        return CategoryRepository.save(category)
    }

    @Override
    Category update(Category category, int id) {
        def record = CategoryRepository.findById(id)
        record.with {
            name = category.name
        }
        CategoryRepository.save(record)
        record
    }

    @Override
    Category delete(int id) {
        def record = CategoryRepository.findById(id)
        CategoryRepository.delete(record)
        record
    }
}