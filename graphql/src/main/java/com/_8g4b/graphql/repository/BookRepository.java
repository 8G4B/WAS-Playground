package com._8g4b.graphql.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com._8g4b.graphql.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}