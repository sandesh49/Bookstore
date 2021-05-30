package com.bookstore.service.impl;

import java.util.List;

import org.apache.mahout.cf.taste.impl.model.jdbc.MySQLJDBCDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;
import org.springframework.stereotype.Service;

import com.mysql.cj.jdbc.MysqlDataSource;

@Service
public class RecommenderService {

	private static final String SERVER_NAME = "localhost";
	 private static final String USER_NAME = "root";
	 private static final String PASSWORD = "hello@123";
	 private static final String DATABASE = "bookstore?autoReconnect=true&useSSL=false";
	 
	 
	 
	 private static final int NEIGHBOR_HOOD_SIZE = 5;
	 
	 public Recommender getRecommender() throws Exception {

		  MysqlDataSource dataSource = new MysqlDataSource();
		  dataSource.setServerName(SERVER_NAME);
		  dataSource.setUser(USER_NAME);
		  dataSource.setPassword(PASSWORD);
		  dataSource.setDatabaseName(DATABASE);

		  DataModel model = new MySQLJDBCDataModel(dataSource,
		    "recommender", "user_id", "book_id", "value", null);

		  /* Get Pearson correlation instance from given model */
		  UserSimilarity similarity = new PearsonCorrelationSimilarity(model);

		  /*
		   * Computes a neighborhood consisting of the nearest n users to a given
		   * user.
		   */
		  UserNeighborhood neighborhood = new NearestNUserNeighborhood(
		    NEIGHBOR_HOOD_SIZE, similarity, model);

		  /* Get Recommender */
		  Recommender recommender = new GenericUserBasedRecommender(model,
		    neighborhood, similarity);

		  return recommender;
		 }

		 /**
		  * Get noOfRecommendations for given customer
		  * 
		  * @param recommender
		  * @param custId
		  * @param noOfRecommendations
		  * @return
		  * @throws Exception
		  */
		 public List<RecommendedItem> getRecommendations(
		   Recommender recommender, int userid, int noOfRecommendations)
		   throws Exception {
		  return recommender.recommend(userid, 5);
		 }

		 public void displayRecommendations(int userid,
		   List<RecommendedItem> recommendations) {
	
			 }

	
}
