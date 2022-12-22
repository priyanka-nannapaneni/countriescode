package com.ibm.countrycodes.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.countrycodes.model.Country;
import com.ibm.countrycodes.repository.CountryCodesRepository;

@RestController
@RequestMapping("/api")
public class CountryCodesController {
	
	Logger logger = LoggerFactory.getLogger(CountryCodesController.class);

	@Autowired
	CountryCodesRepository countryCodesRepository;

	@GetMapping("/countries")
	public ResponseEntity<Map<String, Object>> getCountries(@RequestParam("page") int page,
			@RequestParam("size") int size, @RequestParam(required = false) String q, @RequestParam("sort") String sortType) {
		logger.info("Entered into getCountries method:");
		try {
			Page<Country> sortedCountriesList=null;
			List<Order> orders = new ArrayList<>();
			orders.add(new Order(Sort.Direction.ASC, sortType));

		Pageable pageable = PageRequest.of(page, size,Sort.by(orders));
		if(q==null) {
			logger.info("Entered into getCountries method with search parameter is null");
			sortedCountriesList = countryCodesRepository.findAll(pageable);	
		}
			else
				sortedCountriesList = countryCodesRepository.findByNameOriso3CodeLikeCaseInsensitive(q,pageable);

		if (sortedCountriesList.isEmpty()) 
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	      

	      Map<String, Object> response = new HashMap<>();
	      response.put("countriesList", sortedCountriesList.getContent());
	      logger.info("End of the getCountries method");
	      return new ResponseEntity<>(response, HttpStatus.OK);
		 } catch (Exception e) {
			 logger.debug("Entered into the catch block"+e.getMessage());
	      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
		
		
	}

	
	
}
