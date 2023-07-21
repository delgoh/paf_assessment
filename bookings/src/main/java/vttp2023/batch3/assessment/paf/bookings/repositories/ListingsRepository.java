package vttp2023.batch3.assessment.paf.bookings.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import vttp2023.batch3.assessment.paf.bookings.models.AccomDetails;
import vttp2023.batch3.assessment.paf.bookings.models.AccomSummary;

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
	// db.listings.find(
	// {
	// 		"address.country": <country>,
	// 		accommodates: {$gte: <personCount>},
	// 		price: {$gte: <priceMin>, $lte: <priceMax>}
	// 	},
	// 	{ address: "$address.street", price: 1, image_url : "$images.picture_url"}
	// )
	// .sort({ price: -1 });
	public List<AccomSummary> getRequestedListings(String country, Integer personCount, Double priceMin, Double priceMax) {
		Query query = Query.query(
			new Criteria().andOperator(
				Criteria.where("address.country").is(country),
				Criteria.where("accommodates").gte(personCount),
				Criteria.where("price").gte(priceMin).lte(priceMax)
			)
		);
		query.fields().include("address.street", "price", "images.picture_url");
		query.with(Sort.by(Direction.DESC, "price"));

		return mongoTemplate.find(query, AccomSummary.class, LISTINGS_COLLECTION);
	}


	//TODO: Task 4
	// db.listings.find(
	// 		{_id: "16134812"},
	// 		{
	// 			description: 1, "images.picture_url": 1, price: 1, amenities: 1,
	// 			address: {street: 1, suburb: 1, country: 1}
	// 		}
	// );
	public AccomDetails getAccomDetailsById(String accomId) {
		Query query = Query.query(
			Criteria.where("_id").is(accomId)
		);
		query.fields()
			.include("description", "images.picture_url", "price", "amenities")
			.include("address.street", "address.suburb", "address.country");

		return mongoTemplate.findOne(query, AccomDetails.class, LISTINGS_COLLECTION);
	}
	

	//TODO: Task 5


}
