package com.management.Event.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.management.Event.model.Equipment;
import com.management.Event.model.Event;
import com.management.Event.model.FoodItem;
import com.management.Event.model.Venue;
import com.management.Event.repositories.EquipmentRepo;
import com.management.Event.repositories.EventRepo;
import com.management.Event.repositories.FoodItemRepo;
import com.management.Event.repositories.VenueRepo;

@org.springframework.stereotype.Service
public class VenueServiceImpl implements VenueService {

	@Autowired
	VenueRepo venueRepo;

	@Autowired
	EquipmentRepo equipmentRepo;

	@Autowired
	FoodItemRepo foodItemRepo;

	@Autowired
	EventRepo eventRepo;

	@Override
	public List<Venue> getVenuesByOrganizerId(int orgId) {
		return venueRepo.findByMemberId(orgId);
	}

	// Get Distinct Places
	@Override
	public List<String> getAllDistinctPlaces() {
		List<Venue> venues = (List<Venue>) venueRepo.findAll();
		Set<String> venueSet = venues.stream().map(v -> v.getVenuePlace()).collect(Collectors.toSet());
		List<String> venueList = venueSet.stream().collect(Collectors.toList());
		return venueList;
	}

	// Venues by Place
	@Override
	public List<Venue> getVenueOfPlace(String place) {
		return venueRepo.findByVenuePlace(place);
	}

	// Add Venue
	@Override
	public Venue addVenue(Venue venue) {
		return venueRepo.save(venue);
	}

	// Get Venue by Id
	@Override
	public Venue getVenue(int venueId) {
		return venueRepo.findById(venueId).get();
	}

	// List all venues
	@Override
	public List<Venue> getVenues() {
		return (List<Venue>) venueRepo.findAll();
	}

	// Update..
	@Override
	public int updateVenue(Venue venue) {
		Venue venue2 = venueRepo.findById(venue.getVenueId()).get();
		venue2.setVenueName(venue.getVenueName());
		venue2.setVenuePlace(venue.getVenuePlace());
		venue2.setVenueContact(venue.getVenueContact());

		venueRepo.save(venue2);
		return 1;
	}

	// Delete...
	@Override
	public int deleteVenue(int venueId) {

		List<FoodItem> foodItems = foodItemRepo.findByVenueId(venueId);

		for (FoodItem f : foodItems) {
			foodItemRepo.delete(f);
		}

		List<Event> events = eventRepo.findByVenueId(venueId);
		for (Event e : events) {
			eventRepo.delete(e);
		}

		List<Equipment> equips = equipmentRepo.findByVenueId(venueId);
		for (Equipment eq : equips) {
			equipmentRepo.delete(eq);
		}
		venueRepo.deleteById(venueId);
		return 1;
	}

}
