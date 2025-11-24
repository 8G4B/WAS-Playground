package com._8g4b.xml.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com._8g4b.xml.model.Book;

import jakarta.annotation.PostConstruct;

@Service
public class BookService {

    private final List<Book> books = new ArrayList<>();
    private Long nextId = 1L;

    @PostConstruct
    public void init() {
        books.add(new Book(nextId++, "The Great Gatsby", "F. Scott Fitzgerald", "978-0743273565", 10.99));
        books.add(new Book(nextId++, "To Kill a Mockingbird", "Harper Lee", "978-0061120084", 12.99));
        books.add(new Book(nextId++, "1984", "George Orwell", "978-0451524935", 13.99));
    }

    public List<Book> getAllBooks() {
        return new ArrayList<>(books);
    }

    public Optional<Book> getBookById(Long id) {
        return books.stream().filter(book -> book.getId().equals(id)).findFirst();
    }

    public Book createBook(Book book) {
        book.setId(nextId++);
        books.add(book);
        return book;
    }

    public Optional<Book> updateBook(Long id, Book updatedBook) {
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getId().equals(id)) {
                updatedBook.setId(id);
                books.set(i, updatedBook);
                return Optional.of(updatedBook);
            }
        }
        return Optional.empty();
    }

    public boolean deleteBook(Long id) {
        return books.removeIf(book -> book.getId().equals(id));
    }
}
