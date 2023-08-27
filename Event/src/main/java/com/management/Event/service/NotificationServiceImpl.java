package com.management.Event.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.management.Event.model.Booking;
import com.management.Event.model.BookingDetail;
import com.management.Event.model.Member;
import com.management.Event.model.Notification;
import com.management.Event.model.Venue;
import com.management.Event.repositories.MemberRepo;
import com.management.Event.repositories.NotificationRepo;
import com.management.Event.repositories.VenueRepo;

@org.springframework.stereotype.Service
public class NotificationServiceImpl implements NotificationService {

	@Autowired
	MemberRepo memberRepo;

	@Autowired
	NotificationRepo notificationRepo;

	@Autowired
	BookingService bookingService;

	@Autowired
	VenueRepo venueRepo;

	// Generate Notification On Registration//##
	@Override
	public int notificationOnRegistration(Member member) {
		Member newMember = memberRepo.findByEmail(member.getEmail());

		Notification notification = new Notification();

		notification.setMemberId(newMember.getMemberId());
		LocalDate localDate = LocalDate.now();
		String localTime = LocalTime.now().truncatedTo(ChronoUnit.SECONDS).format(DateTimeFormatter.ISO_LOCAL_TIME);
		notification.setDate(localDate.toString());
		notification.setTime(localTime);
		if (newMember.getRole().equals("user")) {
			String userNoti = "Welcome " + newMember.getFirstName()
					+ ", You have successfully registered on Online Event Management System. "
					+ "Now you can book your events on various venues.";
			notification.setMessage(userNoti);
		} else {
			String orgNoti = "Welcome " + member.getFirstName()
					+ ", You have successfully registered on Online Event Management System. "
					+ "Now you can add your venues on the portal";
			notification.setMessage(orgNoti);
		}

		notificationRepo.save(notification);
		return 1;
	}

	// Generate notification On Booking//##
	@Override
	public int notifyOnBooking(Booking booking) {
		Notification notification = new Notification();

		notification.setMemberId(booking.getMemberId());
		LocalDate localDate = LocalDate.now();
		String localTime = LocalTime.now().truncatedTo(ChronoUnit.SECONDS).format(DateTimeFormatter.ISO_LOCAL_TIME);

		notification.setDate(localDate.toString());
		notification.setTime(localTime);

		String userNoti = "Dear User, Your booking data is stored temprory, Please make it fixed by completing payment";

		notification.setMessage(userNoti);
		notificationRepo.save(notification);
		return 1;

	}

	// Generate notification On payment//##
	@Override
	public int notifyOnPayment(int bookingId) {

		BookingDetail singleBooking = bookingService.bookingDetail(bookingId);

		int memberId = venueRepo.findById(singleBooking.getVenueId()).get().getMemberId();

		Member member = memberRepo.findById(memberId).get();

		String userNoti = "Dear " + singleBooking.getFirstName() + ", Your payment of Rs. "
				+ singleBooking.getTotalCost() + " for booking ID " + singleBooking.getBookingId()
				+ " is processed successfully." + "Your " + singleBooking.getEventName()
				+ " event is booked on Venue Name " + singleBooking.getVenueName() + " at "
				+ singleBooking.getVenuePlace() + " on date " + singleBooking.getDate();

		String orgNoti = "Dear " + member.getFirstName() + ", Your venue " + singleBooking.getVenueName()
				+ " is booked by user " + singleBooking.getFirstName() + " " + singleBooking.getLastName()
				+ " for an event of " + singleBooking.getEventName() + " on date " + singleBooking.getDate();

		Notification notification = new Notification();
		notification.setMemberId(singleBooking.getMemberId());
		LocalDate localDate = LocalDate.now();
		String localTime = LocalTime.now().truncatedTo(ChronoUnit.SECONDS).format(DateTimeFormatter.ISO_LOCAL_TIME);

		notification.setDate(localDate.toString());
		notification.setTime(localTime);
		notification.setMessage(userNoti);

		notificationRepo.save(notification);

		Notification notification2 = new Notification();
		notification2.setMemberId(memberId);
		LocalDate localDate2 = LocalDate.now();
		String localTime2 = LocalTime.now().truncatedTo(ChronoUnit.SECONDS).format(DateTimeFormatter.ISO_LOCAL_TIME);

		notification2.setDate(localDate2.toString());
		notification2.setTime(localTime2);
		notification2.setMessage(orgNoti);
		notificationRepo.save(notification2);
		return 1;

	}

	// Genetate notification on New Venue Add//##
	@Override
	public int notifyOnVenueAdd(Venue venue) {

		Member member = memberRepo.findById(venue.getMemberId()).get();

		String orgNoti = "Dear " + member.getFirstName() + " Your venue with name " + venue.getVenueName()
				+ " at place " + venue.getVenuePlace() + " is Successfully added. ";

		Notification notification = new Notification();
		notification.setMemberId(venue.getMemberId());
		LocalDate localDate = LocalDate.now();
		String localTime = LocalTime.now().truncatedTo(ChronoUnit.SECONDS).format(DateTimeFormatter.ISO_LOCAL_TIME);
		notification.setDate(localDate.toString());
		notification.setTime(localTime);
		notification.setMessage(orgNoti);
		notificationRepo.save(notification);
		return 1;

	}

	// Get List of Notifications
	@Override
	public List<Notification> getNotifications(int memberId) {
		return notificationRepo.findByMemberId(memberId);
	}

	// Delete Notifications
	@Override
	public int deleteNotification(int notificationId) {
		notificationRepo.deleteById(notificationId);
		return 1;
	}

}
