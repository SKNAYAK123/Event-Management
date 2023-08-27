package com.management.Event.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.management.Event.model.Venue;

@Repository
public interface VenueRepo extends CrudRepository<Venue, Integer> {

	List<Venue> findByMemberId(int orgId);

	List<Venue> findByVenuePlace(String place);

}
