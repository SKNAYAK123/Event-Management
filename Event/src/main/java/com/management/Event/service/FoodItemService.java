package com.management.Event.service;

import java.util.List;

import com.management.Event.model.FoodItem;


public interface FoodItemService {

	public List<FoodItem> getFoodItemsByVenueId(int venueId);

	public FoodItem addFoodItem(FoodItem foodItem);

	public FoodItem getFoodItem(int foodItemId);

	public int updateFoodItem(FoodItem foodItem);

	public int deleteFoodItem(int foodItemId);

}
