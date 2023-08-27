package com.management.Event.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.management.Event.model.Equipment;

@Repository
public interface EquipmentRepo extends CrudRepository<Equipment, Integer> {

	List<Equipment> findByVenueId(int venueId);

}
