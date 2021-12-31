package com.exa.api.repository;

import java.util.List;

import com.exa.api.Books;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

interface CategoryBooksRepository extends JpaRepository<Books, Integer> {
    String rawQuery = "select * from books b where b.category_id =?1";

    @Query(nativeQuery = true, value = rawQuery)
    List<Books> findAllByBookId(Integer bookid);
}
