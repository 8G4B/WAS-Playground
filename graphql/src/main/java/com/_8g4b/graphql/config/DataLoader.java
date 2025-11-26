package com._8g4b.graphql.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com._8g4b.graphql.entity.Author;
import com._8g4b.graphql.entity.Book;
import com._8g4b.graphql.repository.AuthorRepository;
import com._8g4b.graphql.repository.BookRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    @Override
    public void run(String... args) {
        // Create authors
        Author author1 = Author.builder()
                .name("Martin Fowler")
                .email("martin@fowler.com")
                .build();

        Author author2 = Author.builder()
                .name("Robert C. Martin")
                .email("uncle@bob.com")
                .build();

        authorRepository.save(author1);
        authorRepository.save(author2);

        // Create books
        Book book1 = Book.builder()
                .title("Refactoring")
                .isbn("978-0134757599")
                .pageCount(448)
                .author(author1)
                .build();

        Book book2 = Book.builder()
                .title("Patterns of Enterprise Application Architecture")
                .isbn("978-0321127426")
                .pageCount(560)
                .author(author1)
                .build();

        Book book3 = Book.builder()
                .title("Clean Code")
                .isbn("978-0132350884")
                .pageCount(464)
                .author(author2)
                .build();

        Book book4 = Book.builder()
                .title("Clean Architecture")
                .isbn("978-0134494166")
                .pageCount(432)
                .author(author2)
                .build();

        bookRepository.save(book1);
        bookRepository.save(book2);
        bookRepository.save(book3);
        bookRepository.save(book4);

        System.out.println("Sample data loaded successfully!");
    }
}