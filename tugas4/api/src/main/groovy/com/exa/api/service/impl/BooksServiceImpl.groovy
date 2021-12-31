package com.exa.api.service.impl

import com.exa.api.entity.Category
import com.exa.api.entity.CategoryBook
import com.exa.api.entity.Books
import com.exa.api.dto.CategoryDTO
import com.exa.api.dto.BooksDTO
import com.exa.api.repository.CategoryRepository
import com.exa.api.repository.BooksRepository
import com.exa.api.repository.CategoryBooksRepository
import com.exa.api.service.CategoryService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class BooksServiceImpl implements BookService {
    @Autowired
    private final BooksRepository BookRepository
    @Autowired
    private final CategoryRepository CategoryRepository

    @Override List<Books> findAll(String include) {
        List<Books> books;
        books = BookRepository.findAll()
        List<Books> booksList = new ArrayList<>();

        if (books != null) {
            for (Books book : books) {
                BooksDTO bookDTO = new BooksDTO();
                bookDTO.setId(book.getId());
                bookDTO.setName(book.getName());
                bookDTO.setIsbn(book.getIsbn());
                bookDTO.setCategory_id(book.getCategory_id());

                List<Category> categoryBooks = null;
                if (include == "categories") {
                    categoryBooks = CategoryRepository.findCategory(book.getCategory_id());
                }
                List<BooksDTO> categoryDTOList = new ArrayList<>();
                if (categoryBooks != null) {
                    for (Category categoryBook : categoryBooks) {
                        CategoryDTO categoryDTO = new CategoryDTO();
                        categoryDTO.setId(categoryBook.getId());
                        categoryDTO.setName(categoryBook.getName());

                        categoryDTOList.add(categoryDTO);
                    }

                    bookDTO.setCategory(categoryDTOList);
                }

                booksList.add(bookDTO);
            }
        }
        return booksList;
    }

    @Override
    BookDTO findById(int id, String include) {
        Books book;
        List<Category> categoryBooks = null;

        book = BooksRepository.findById(id);

        if (include == "categories") {
            categoryBooks = CategoryRepository.findCategory(book.getCategory_id());
        }

        BooksDTO bookDTO = new BooksDTO();
        bookDTO.setId(book.getId());
        bookDTO.setName(book.getName());
        bookDTO.setIsbn(book.getIsbn());
        bookDTO.setCategory_id(book.getCategory_id());

        List<BooksDTO> categoryDTOList = new ArrayList<>();
        if (categoryBooks != null) {
            for (Category categoryBook : categoryBooks) {
                CategoryDTO categoryDTO = new CategoryDTO();
                categoryDTO.setId(categoryBook.getId());
                categoryDTO.setName(categoryBook.getName());

                categoryDTOList.add(categoryDTO);
            }

            bookDTO.setCategory(categoryDTOList);
        }
        return bookDTO;
    }

    @Override
    Book save(Books book) {
        BooksRepository.save(book)
    }

    @Override
    Book update(Books book, int id) {
        def record = BooksRepository.findById(id)
        record.with {
            name = book.name
            isbn = book.isbn
            category_id = book.category_id
        }
        BooksRepository.save(record)
        record
    }

    @Override
    Book delete(int id) {
        def record = BooksRepository.findById(id)
        BooksRepository.delete(record)
        record
    }
}