package com.bookstore.service.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookstore.domain.Review;
import com.bookstore.repository.ReviewRepository;
import com.bookstore.service.ReviewService;

import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.CoreSentence;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;

@Service
public class ReviewServiceImpl implements ReviewService{

	@Autowired
	private ReviewRepository reviewRepository;
	
	@Override
	public Review save(Review review) {
		return reviewRepository.save(review);
	}
	
	@Override
	public void saveRecommend(int userid, int bookid, int rating) throws ClassNotFoundException,SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			 conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookstore?useSSL=false&amp;serverTimezone=UTC","root","hello@123");
			 
			 String sql="insert into recommender"
						+"(user_id,book_id,value)"
						+"values(?,?,?)";
				stmt=conn.prepareStatement(sql);
				stmt.setInt(1, userid);
				stmt.setInt(2, bookid);
				stmt.setInt(3, rating);
				
				stmt.execute();
		} finally {
			close(conn,stmt,null);
		}
		
	}

	@Override
	public List<Review> getReview(Long id) throws SQLException, ClassNotFoundException {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		List<Review> reviews = new ArrayList<>();
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			 conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookstore?useSSL=false&amp;serverTimezone=UTC","root","hello@123");
			 stmt = conn.createStatement();
			 rs = stmt.executeQuery("SELECT description FROM review WHERE description!='' AND description IS NOT NULL AND bookid="+id);
			while(rs.next()) {
				
				String description = rs.getString("description");
				
				Review tempReview = new Review(description);
				reviews.add(tempReview);
			}
			return reviews;
		} finally {
			close(conn,stmt,rs);
		}
		
		
	}
	
	@Override
	public List<String> getFeedback(Long id) throws SQLException, ClassNotFoundException {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		List<String> feedback = new ArrayList<>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("The is is :"+id);
			 conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookstore?useSSL=false&amp;serverTimezone=UTC","root","hello@123");
			 stmt = conn.createStatement();
			 rs = stmt.executeQuery("select description from review where bookid="+id);
			while(rs.next()) {
				
				StanfordCoreNLP stanfordCoreNLP = Pipeline.getPipeline();

				String description = rs.getString("description");

		        CoreDocument coreDocument = new CoreDocument(description);

		        stanfordCoreNLP.annotate(coreDocument);

		        List<CoreSentence> sentences = coreDocument.sentences();

		        for(CoreSentence sentence : sentences) {

		            String sentiment = sentence.sentiment();
		            feedback.add(sentiment);
		        }
				
				
			}
			System.out.println(feedback);
			return feedback;
		} finally {
			close(conn,stmt,rs);
		}
		
		
	}
	
	@Override
	public double getRating(Long id) throws SQLException, ClassNotFoundException {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		double sum1=0;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			 conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookstore?useSSL=false&amp;serverTimezone=UTC","root","hello@123");
			 stmt = conn.createStatement();
			 
			 rs = stmt.executeQuery("select avg(rating) from review where bookid="+id);
			 
				while(rs.next()) {
					
					String rating = rs.getString(1);
					if(rating==null) {
					 sum1=0;
					 return sum1;
					}
					sum1 = Double.parseDouble(rating);
				}
			return sum1;
		} finally {
			close(conn,stmt,rs);
		}
		
		
	}
		
	private void close(Connection conn, Statement stmt, ResultSet rs) {
		try {
			if(rs!=null) {
				rs.close();
			}
			if(stmt!=null) {
				stmt.close();
			}
			
			
			if(conn!=null) {
				conn.close();
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	
	
}
