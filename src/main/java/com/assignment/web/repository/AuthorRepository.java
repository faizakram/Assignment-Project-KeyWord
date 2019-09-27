package com.assignment.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.assignment.web.model.Author;


@Repository
public interface AuthorRepository  extends JpaRepository<Author, Long> {

}
