package com.management.Event.service;

import java.sql.Date;
import java.util.List;

import com.management.Event.model.Booking;
import com.management.Event.model.BookingDetail;



public interface BookingService {
	
	public Booking rectifyBooking(Booking booking);

	public List<BookingDetail> getBookingDetailByOrganizerId(int orgId,String tense);
	
	public List<Date> getUpcomingBookedDates(int venueId);

	public Boolean isAvailable(Booking booking);//**

	public Boolean isAvailableForPayment(int tempBookingId);//**

	public Booking getBooking(int bookingId);

	public List<BookingDetail> getBookings();

	public List<BookingDetail> getBookingsByUserId(int userId);
	
	public BookingDetail bookingDetail(int bookingId);

	public int payment(int bookingId);//**

	public int deleteBooking(int bookingId);

	public int checkActiveBookings(int venueId);//**

}
