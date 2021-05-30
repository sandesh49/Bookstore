package com.bookstore.service;

import java.sql.SQLException;
import java.util.List;

import com.bookstore.domain.Review;

public interface ReviewService {

	Review save(Review review);
	
	public List<Review> getReview(Long id) throws SQLException, ClassNotFoundException;

	List<String> getFeedback(Long id) throws SQLException, ClassNotFoundException;

	double getRating(Long id) throws SQLException, ClassNotFoundException;
	
	public void saveRecommend(int bookid,int userid,int rating) throws SQLException, ClassNotFoundException;
}
