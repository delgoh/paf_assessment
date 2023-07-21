package vttp2023.batch3.assessment.paf.bookings.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import jakarta.validation.Valid;
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
	

	//TODO: Task 5


}
