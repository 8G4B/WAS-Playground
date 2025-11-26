package com._8g4b.graphql.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com._8g4b.graphql.entity.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
}