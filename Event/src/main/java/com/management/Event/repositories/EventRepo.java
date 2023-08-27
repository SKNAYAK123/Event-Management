package com.management.Event.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.management.Event.model.Event;

@Repository
public interface EventRepo extends CrudRepository<Event, Integer> {

	List<Event> findByVenueId(int venueId);

	Event findByEventNameAndVenueId(String eName, int vId);

}
