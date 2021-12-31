package com.exa.api.controller

import com.exa.api.entity.Books
import com.exa.api.dto.BooksDTO
import com.exa.api.service.BooksService
import org.springframework.web.bind.annotation.*
import org.springframework.beans.factory.annotation.Autowired

@RestController
@RequestMapping('/books')
class BooksController {
    @Autowired
    private final BooksService bookService
    @GetMapping('')
    List<Books> findAll(
        @RequestParam(value="include",required = false) String include
    ) {
        bookService.findAll(include)
    }

    @GetMapping('{id}')
    BooksDTO findById(
        @PathVariable('id') int id,
        @RequestParam(value="include",required = false) String include
    ) {
        bookService.findById(id,include)
    }

    @PostMapping()
    Books save(@RequestBody Books book) {
        bookService.save(book)
    }

    @PutMapping('{id}')
    Books update(@RequestBody Books book, @PathVariable('id') int id) {
        bookService.update(book, id)
    }

    @DeleteMapping('{id}')
    Books delete(@PathVariable('id') int id) {
        bookService.delete(id)
    }
}