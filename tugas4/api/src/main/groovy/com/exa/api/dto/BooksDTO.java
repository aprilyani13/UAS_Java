package com.exa.api.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

public class BooksDTO {
    private Long id;
    private String name;
    private String isbn;
    private Long category_id;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<CategoryDTO> category;

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

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Long getCategory_id() {
        return category_id;
    }

    public void setCategory_id(Long category_id) {
        this.category_id = category_id;
    }

    public List<CategoryDTO> getCategory() {
        return category;
    }

    public void setCategory(List<CategoryDTO> category) {
        this.category = category;
    }
}
