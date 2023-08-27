package com.management.Event.service;

import java.util.List;

import com.management.Event.model.Venue;


public interface VenueService {

	public List<Venue> getVenuesByOrganizerId(int orgId);

	public List<String> getAllDistinctPlaces();

	public List<Venue> getVenueOfPlace(String place);

	public Venue addVenue(Venue venue);

	public Venue getVenue(int venueId);

	public List<Venue> getVenues();

	public int updateVenue(Venue venue);

	public int deleteVenue(int venueId);

}
