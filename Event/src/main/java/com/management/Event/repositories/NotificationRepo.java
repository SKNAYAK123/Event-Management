package com.management.Event.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.management.Event.model.Notification;

public interface NotificationRepo extends CrudRepository<Notification, Integer> {

	List<Notification> findByMemberId(int memberId);

}
