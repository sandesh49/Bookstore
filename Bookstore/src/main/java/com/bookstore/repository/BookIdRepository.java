package com.bookstore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookstore.domain.Book;

public interface BookIdRepository extends JpaRepository<Book, Long>{

	List<Book> findAllById(Long bookid2);
}
