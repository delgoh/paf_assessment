package vttp2023.batch3.assessment.paf.bookings.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import vttp2023.batch3.assessment.paf.bookings.models.Booking;
import vttp2023.batch3.assessment.paf.bookings.models.SearchRequest;
import vttp2023.batch3.assessment.paf.bookings.services.ListingsService;

@Controller
public class ListingsController {

	@Autowired
	private ListingsService listingsService;

	//TODO: Task 2
	@GetMapping("/")
	public String viewLandingPage(Model model) {
		model.addAttribute("searchRequest", new SearchRequest());
		model.addAttribute("countriesList", listingsService.getAllCountries());
		return "landingPage";
	}
	
	//TODO: Task 3
	@GetMapping("/search")
	public String showListings(@Valid @ModelAttribute SearchRequest searchRequest, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("countriesList", listingsService.getAllCountries());
			return "landingPage";
		}
		model.addAttribute("searchedCountry", searchRequest.getCountry());
		model.addAttribute("accomListings", listingsService.getRequestedListings(
			searchRequest.getCountry(),
			searchRequest.getPersonCount(),
			searchRequest.getPriceMin(),
			searchRequest.getPriceMax()
		));
		return "listingsPage";
	}


	//TODO: Task 4
	@GetMapping("/accommodation/{accommodation-id}")
	public String showAccommodationDetailsById(@PathVariable("accommodation-id") String accomId, WebRequest req, Model model, HttpSession session) {
		model.addAttribute("searchUrl", req.getHeader("Referer"));
		model.addAttribute("accomDetails", listingsService.getAccomDetailsById(accomId));
		model.addAttribute("booking", new Booking());
		session.setAttribute("accomId", accomId);
		return "accomDetails";
	}
	

	//TODO: Task 5
	@PostMapping(path = "/bookAccommodation", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public String bookAccommodation(@ModelAttribute("booking") Booking booking, HttpSession session) {
		// Boolean isAccomAvailable = listingsService.checkAccomAvailability(booking.getStayDuration(), (String) session.getAttribute("accomId"));
		
		// if (!isAccomAvailable) {
		// 	// to fill
		// }

		Boolean isBookingSuccessful = listingsService.performBooking(booking.getStayDuration(), (String) session.getAttribute("accomId"), booking);
		System.out.println("booking status: " + isBookingSuccessful);

		return "reservationSuccess";
	}


}
