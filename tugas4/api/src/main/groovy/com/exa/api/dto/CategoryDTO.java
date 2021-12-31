package com.exa.api.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

public class CategoryDTO {
    private Long id;
    private String name;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<BooksDTO> books;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<BooksDTO> getBooks() {
        return books;
    }

    public void setBooks(List<BooksDTO> books) {
        this.books = books;
    }
}
