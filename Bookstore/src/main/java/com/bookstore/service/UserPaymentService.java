package com.bookstore.service;

import java.util.Optional;

import com.bookstore.domain.UserPayment;

public interface UserPaymentService {

	Optional<UserPayment> findById(Long id);
	
	void removeById(Long id);
}
