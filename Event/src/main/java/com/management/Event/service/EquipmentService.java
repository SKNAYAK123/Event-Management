package com.management.Event.service;

import java.util.List;

import com.management.Event.model.Equipment;


public interface EquipmentService {

	public List<Equipment> getEquipmentsByVenueId(int venueId);

	public Equipment addEquipment(Equipment equipment);

	public Equipment getEquipment(int equipmentId);

	public int updateEquipment(Equipment equipment);

	public int deleteEquipment(int equipmentId);

}
