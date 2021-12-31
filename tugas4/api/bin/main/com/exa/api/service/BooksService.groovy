package com.exa.api.service

import com.exa.api.dto.BooksDTO
import com.exa.api.entity.Books

interface BooksService {
    List<Books> findAll(String include)
    BooksDTO findById(int id, String include)
    Books save(Books books)
    Books update(Books books, int id)
    Books delete(int id)
}