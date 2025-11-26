package com._8g4b.graphql.controller;

import java.util.List;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com._8g4b.graphql.entity.Author;
import com._8g4b.graphql.entity.Book;
import com._8g4b.graphql.repository.AuthorRepository;
import com._8g4b.graphql.repository.BookRepository;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class BookController {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    @QueryMapping
    public List<Book> books() {
        return bookRepository.findAll();
    }

    @QueryMapping
    public Book book(@Argument Long id) {
        return bookRepository.findById(id).orElse(null);
    }

    @MutationMapping
    public Book createBook(@Argument CreateBookInput input) {
        Author author = authorRepository.findById(input.authorId())
                .orElseThrow(() -> new RuntimeException("Author not found"));

        Book book = Book.builder()
                .title(input.title())
                .isbn(input.isbn())
                .pageCount(input.pageCount())
                .author(author)
                .build();

        return bookRepository.save(book);
    }

    @MutationMapping
    public Boolean deleteBook(@Argument Long id) {
        if (bookRepository.existsById(id)) {
            bookRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public record CreateBookInput(String title, String isbn, Integer pageCount, Long authorId) {
    }
}