package com.management.Event.service;

import java.util.List;

import com.management.Event.model.Booking;
import com.management.Event.model.Member;
import com.management.Event.model.Notification;
import com.management.Event.model.Venue;

public interface NotificationService {
	
	public int notificationOnRegistration(Member member); 

	public int notifyOnPayment(int bookingId);

	public int notifyOnBooking(Booking booking);
	
	public int notifyOnVenueAdd(Venue venue);

	public int deleteNotification(int notificationId);

	public List<Notification> getNotifications(int memberId);
	
}
