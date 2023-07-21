package vttp2023.batch3.assessment.paf.bookings.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class ListingsRepository {

	@Autowired
	private MongoTemplate mongoTemplate;

	private final String LISTINGS_COLLECTION = "listings";

	//TODO: Task 2
	// db.listings.distinct("address.country");
	public List<String> getAllCountries() {
		return mongoTemplate.findDistinct(new Query(), "address.country", LISTINGS_COLLECTION, String.class);
	}
	
	//TODO: Task 3
	// db.listings.find({"address.country": <country>});


	//TODO: Task 4
	

	//TODO: Task 5


}
