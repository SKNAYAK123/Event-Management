package com.management.Event.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.management.Event.model.FoodItem;

@Repository
public interface FoodItemRepo extends CrudRepository<FoodItem, Integer> {

	List<FoodItem> findByVenueId(int venueId);

}
