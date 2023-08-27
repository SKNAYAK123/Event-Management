package com.management.Event.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.management.Event.model.FoodItem;
import com.management.Event.repositories.FoodItemRepo;

@Service
public class FoodItemServiceImpl implements FoodItemService {

	@Autowired
	FoodItemRepo foodItemRepo;;

	// Get food Items by venueId
	@Override
	public List<FoodItem> getFoodItemsByVenueId(int venueId) {
		return foodItemRepo.findByVenueId(venueId);
	}

	// Add...
	@Override
	public FoodItem addFoodItem(FoodItem foodItem) {
		return foodItemRepo.save(foodItem);
	}

	// Get...
	@Override
	public FoodItem getFoodItem(int foodItemId) {
		return foodItemRepo.findById(foodItemId).get();
	}

	// Update...
	@Override
	public int updateFoodItem(FoodItem foodItem) {
		FoodItem foodItem2 = foodItemRepo.findById(foodItem.getFoodItemId()).get();
		foodItem2.setFoodItemCost(foodItem.getFoodItemCost());
		foodItem2.setFoodItemName(foodItem.getFoodItemName());
		foodItem2.setVenueId(foodItem.getVenueId());
		foodItemRepo.save(foodItem2);
		return 1;
	}

	// Delete...
	@Override
	public int deleteFoodItem(int foodItemId) {
		foodItemRepo.deleteById(foodItemId);
		return 1;
	}

}
