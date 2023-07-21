package vttp2023.batch3.assessment.paf.bookings.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vttp2023.batch3.assessment.paf.bookings.models.AccomDetails;
import vttp2023.batch3.assessment.paf.bookings.models.AccomSummary;
import vttp2023.batch3.assessment.paf.bookings.models.Booking;
import vttp2023.batch3.assessment.paf.bookings.repositories.ListingsRepository;

@Service
public class ListingsService {

	@Autowired
	private ListingsRepository listingsRepository;
	
	//TODO: Task 2
	public List<String> getAllCountries() {
		return listingsRepository.getAllCountries();
	}
	
	//TODO: Task 3
	public List<AccomSummary> getRequestedListings(String country, Integer personCount, Double priceMin, Double priceMax) {
		return listingsRepository.getRequestedListings(country, personCount, priceMin, priceMax);
	}

	//TODO: Task 4
	public AccomDetails getAccomDetailsById(String accomId) {
		return listingsRepository.getAccomDetailsById(accomId);
	}
	

	//TODO: Task 5
	// public Boolean checkAccomAvailability(Integer stayDuration, String accomId) {
	// 	Integer vacancy = listingsRepository.getOccupancyById(accomId);
	// 	return vacancy != null && vacancy >= stayDuration;
	// }

	@Transactional
	public Boolean performBooking(Integer stayDuration, String accomId, Booking booking) {
		Integer vacancy = listingsRepository.getOccupancyById(accomId);
		if (vacancy < stayDuration) {
			// System.out.println("vacancy failed! vacancy: " + vacancy);
			return false;
		}

		String newId = UUID.randomUUID().toString().replace("_", "").substring(0, 8);
		// System.out.println("new string created: " + newId);
		if (!listingsRepository.insertReservation(accomId, newId, booking)) {
			return false;
		}

		return listingsRepository.updateVacancy(accomId, vacancy - stayDuration);
	}


}
