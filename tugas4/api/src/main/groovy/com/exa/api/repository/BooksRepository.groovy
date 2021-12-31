package com.exa.api.repository

import com.exa.api.entity.Books
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface BooksRepository extends JpaRepository<Books, Integer> {
    List<Books> findAll()
    Books findById(Integer id)
    Books save(Books books)
    void delete(Books books)

    String rawQuery = "select * from books b";

    @Query(nativeQuery = true, value = rawQuery)
    List<Category> findAllBooks();
}