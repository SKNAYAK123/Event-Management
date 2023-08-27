package com.management.Event.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.management.Event.model.Booking;
import com.management.Event.model.Member;
import com.management.Event.model.Venue;

@Repository
public interface BookingRepo extends CrudRepository<Booking, Integer> {

	public List<Booking> findByVenue(Venue venue);


	public List<Booking> findBookingByVenueAndPaymentStatusOrderByDate(Venue venue, String string);

	public List<Booking> findByMember(Member member);

}
