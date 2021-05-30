package com.bookstore.repository;

import org.springframework.data.repository.CrudRepository;

import com.bookstore.domain.Review;

public interface ReviewRepository extends CrudRepository<Review, Integer>{

	
}
