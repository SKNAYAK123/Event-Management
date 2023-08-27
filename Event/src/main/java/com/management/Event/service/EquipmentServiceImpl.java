package com.management.Event.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.management.Event.model.Equipment;
import com.management.Event.repositories.EquipmentRepo;

@org.springframework.stereotype.Service
public class EquipmentServiceImpl implements EquipmentService {

	@Autowired
	EquipmentRepo equipmentRepo;

	// get equipment by venueId
	@Override
	public List<Equipment> getEquipmentsByVenueId(int venueId) {
		return equipmentRepo.findByVenueId(venueId);
	}

	// add equipment
	@Override
	public Equipment addEquipment(Equipment equipment) {
		return equipmentRepo.save(equipment);
	}

	// get equipment
	@Override
	public Equipment getEquipment(int equipmentId) {
		return equipmentRepo.findById(equipmentId).get();
	}

	// update equipment
	@Override
	public int updateEquipment(Equipment equipment) {
		Equipment equipment2 = equipmentRepo.findById(equipment.getEquipmentId()).get();
		equipment2.setEquipmentCost(equipment.getEquipmentCost());
		equipment2.setEquipmentName(equipment.getEquipmentName());
		equipmentRepo.save(equipment2);
		return 1;
	}

	// delete equipment
	@Override
	public int deleteEquipment(int equipmentId) {
		equipmentRepo.deleteById(equipmentId);
		return 1;
	}

}
