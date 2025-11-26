package com._8g4b.graphql.controller;

import java.util.List;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com._8g4b.graphql.entity.Author;
import com._8g4b.graphql.repository.AuthorRepository;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorRepository authorRepository;

    @QueryMapping
    public List<Author> authors() {
        return authorRepository.findAll();
    }

    @QueryMapping
    public Author author(@Argument Long id) {
        return authorRepository.findById(id).orElse(null);
    }

    @MutationMapping
    public Author createAuthor(@Argument CreateAuthorInput input) {
        Author author = Author.builder().name(input.name()).email(input.email()).build();
        return authorRepository.save(author);
    }

    @MutationMapping
    public Boolean deleteAuthor(@Argument Long id) {
        if (authorRepository.existsById(id)) {
            authorRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public record CreateAuthorInput(String name, String email) {
    }
}
