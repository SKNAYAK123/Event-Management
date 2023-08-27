package com.management.Event.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;

import com.management.Event.model.Booking;
import com.management.Event.model.BookingDetail;
import com.management.Event.model.Equipment;
import com.management.Event.model.Event;
import com.management.Event.model.FoodItem;
import com.management.Event.model.Member;
import com.management.Event.model.Venue;
import com.management.Event.repositories.BookingRepo;
import com.management.Event.repositories.EquipmentRepo;
import com.management.Event.repositories.EventRepo;
import com.management.Event.repositories.FoodItemRepo;
import com.management.Event.repositories.MemberRepo;
import com.management.Event.repositories.VenueRepo;

@org.springframework.stereotype.Service
public class BookingServiceImpl implements BookingService {
	Booking newBooking = new Booking();

	@Autowired
	BookingRepo bookingRepo;
	
	@Autowired
	MemberRepo memberRepo;

	@Autowired
	FoodItemRepo foodItemRepository;

	@Autowired
	EquipmentRepo equipmentRepo;

	@Autowired
	EventRepo eventRepo;

	@Autowired
	VenueRepo venueRepo;

	int foodItemCost;
	int equipmentCost;
	int eventCost;
	int totalCost;
	String foodNames;
	String equipmentNames;

	// Convert all raw data of booking from front end to good database form and save
	// to database//####
	@Override
	public Booking rectifyBooking(Booking localBooking) {
		this.foodItemCost = 0;
		this.equipmentCost = 0;
		this.eventCost = 0;
		this.totalCost = 0;
		this.foodNames = " ";
		this.equipmentNames = " ";

		// Get array of selected food Item Id in string format
		String[] selFood = localBooking.getSelectedFoodItems().trim().split(",");

		// Get food Items Cost and Names
		for (String f : selFood) {
			if (!(f.equals(""))) {

				int fId = Integer.parseInt(f);
				FoodItem food = foodItemRepository.findById(fId).get();
				int ownCost = food.getFoodItemCost();
				this.foodItemCost = this.foodItemCost + ownCost;
				this.foodNames = this.foodNames + " " + food.getFoodItemName();
				;
			}

		}

		// Get array of selected Equipments id in string format
		String[] selEquip = localBooking.getSelectedEquipments().split(",");

		// Get Equipment Cost and Names
		for (String e : selEquip) {
			if (!(e.equals(""))) {

				int eId = Integer.parseInt(e);
				Equipment equip = equipmentRepo.findById(eId).get();
				int ownCost = equip.getEquipmentCost();
				this.equipmentCost = this.equipmentCost + ownCost;
				this.equipmentNames = this.equipmentNames + " " + equip.getEquipmentName();
			}
		}

		// Get Event Cost

		String eventName = localBooking.getEventName();
		List<Event> events = eventRepo.findByVenueId(localBooking.getVenueId());
		for (Event e : events) {

			if (eventName.equals(e.getEventName())) {
				this.eventCost = e.getEventCost();
			}
		}

		// Final Food Item and Total Cost
		this.foodItemCost = this.foodItemCost * localBooking.getGuestCount();
		this.totalCost = this.foodItemCost + this.equipmentCost + this.eventCost;

		newBooking.setBookingId(localBooking.getBookingId());
		newBooking.setDate(localBooking.getDate());
		newBooking.setGuestCount(localBooking.getGuestCount());
		newBooking.setEventName(localBooking.getEventName());
		newBooking.setEquipmentName(this.equipmentNames);
		newBooking.setFoodItemName(this.foodNames);
		newBooking.setEventCost(this.eventCost);
		newBooking.setEquipmentCost(this.equipmentCost);
		newBooking.setFoodItemCost(this.foodItemCost);
		newBooking.setTotalCost(this.totalCost);
		newBooking.setPaymentStatus(localBooking.getPaymentStatus());
		newBooking.setMember(memberRepo.findById(localBooking.getMemberId()).get());
		newBooking.setVenue(venueRepo.findById(localBooking.getVenueId()).get());
		newBooking.setDelStatus(1);
		newBooking.setVenueId(localBooking.getVenueId());
		newBooking.setMemberId(localBooking.getMemberId());

		return bookingRepo.save(newBooking);

	}

///////////////////////////// Date related processing.//////////////////////////////////////////////////////
	List<BookingDetail> upcomingBookings;
	List<BookingDetail> historyBookings;

	// this Method will return all upcoming and paid bookings//####
	@Override
	public List<BookingDetail> getBookingDetailByOrganizerId(int orgId, String tense) {

		List<Venue> venueList = venueRepo.findByMemberId(orgId);

		this.upcomingBookings = new ArrayList<>();
		this.historyBookings = new ArrayList<>();

		for (Venue v : venueList) {

			Venue venue = venueRepo.findById(v.getVenueId()).get();

			List<Booking> bookingList = bookingRepo.findByVenue(venue);

			List<BookingDetail> localList = copyDataFromBookingToBookingDetail(bookingList);

			for (BookingDetail bd : localList) {
				// int delStatus = bd.getDelStatus();
				LocalDate todayDate = LocalDate.now();
				LocalDate bookingDate = sqlToLocalDateConverter(bd.getDate());
				String paymentStatus = bd.getPaymentStatus();
				if ((todayDate.isBefore(bookingDate) || todayDate.isEqual(bookingDate))
						&& paymentStatus.equals("Processed")) {

					this.upcomingBookings.add(bd);
				} else if (todayDate.isAfter(bookingDate) && paymentStatus.equals("Processed")) {
					/* && (delStatus==1 || delStatus==11 || delStatus==110 || delStatus==111 ) */
					this.historyBookings.add(bd);

				}
			}
		}
		if (tense.equals("Past")) {
			return this.historyBookings;
		} else {
			return this.upcomingBookings;
		}

	}

	// method to convert sqlDate to localDate//####
	public LocalDate sqlToLocalDateConverter(Date sqlDate) {
		return Date.valueOf(sqlDate.toString()).toLocalDate();
	}

	List<Date> allDates;
	List<Date> requiredDates;

	// Get all the upcoming dates only for particular venue//####
	@Override
	public List<Date> getUpcomingBookedDates(int venueId) {
		requiredDates = new ArrayList<>();
		LocalDate today = LocalDate.now();
		Venue venue = venueRepo.findById(venueId).get();
//		this.allDates = this.bookingDao.getBookedDatesByVenueId(venueId);
		  List<Booking> bookings = bookingRepo.findBookingByVenueAndPaymentStatusOrderByDate(venue, "Processed");
		
		  List<Date> dates = bookings.stream().map(m -> m.getDate()).collect(Collectors.toList());
		  
		  
		for (Date d : dates) {
			LocalDate bookedDate = sqlToLocalDateConverter(d);
			if ((today.isBefore(bookedDate) || today.isEqual(bookedDate))) {
				this.requiredDates.add(d);
			}
		}
		return this.requiredDates;
	}

	Boolean flag;

	// Check if date is already booked and Payment is Done.//####
	// return false if date is already booked or return true
	@Override
	public Boolean isAvailable(Booking tempBooking) {
		flag = true;
		String stringTempDate = tempBooking.getDate().toString();
		List<Date> alreadyBookedDates = this.getUpcomingBookedDates(tempBooking.getVenueId());

		for (Date bookedate : alreadyBookedDates) {

			String stringbookedate = bookedate.toString();
			if (stringbookedate.equals(stringTempDate)) {
				this.flag = false;
			}
		}

		return this.flag;
	}

	// Check if booking is Available for payment with booking Id//####
	@Override
	public Boolean isAvailableForPayment(int tempBookingId) {
		flag = true;
		// get details of booking by booking id
		Booking b = bookingRepo.findById(tempBookingId).get();

		// get the date of booking
		Date bDate = b.getDate();
		// get venue id from booking
		int venueId = b.getVenue().getVenueId();
		// call service get upcoming booking dates
		List<Date> alreadyBookedDates = this.getUpcomingBookedDates(venueId);
		// convert date to string
		String stringBDate = bDate.toString();
		// if the date matches with the already booked and paid dates then return false
		// otherwise return true
		for (Date bookedDate : alreadyBookedDates) {

			String stringBookedDate = bookedDate.toString();
			if (stringBookedDate.equals(stringBDate)) {
				this.flag = false;
			}
		}
		return this.flag;
	}

	// Service to get Booking data by booking ID//####
	@Override
	public Booking getBooking(int bookingId) {
		return bookingRepo.findById(bookingId).get();
	}

	// Service to get list of all bookings//####
	@Override
	public List<BookingDetail> getBookings() {

		List<Booking> bookingList = (List<Booking>) bookingRepo.findAll();

		return copyDataFromBookingToBookingDetail(bookingList);
	}

	private List<BookingDetail> copyDataFromBookingToBookingDetail(List<Booking> bookingList) {

		List<BookingDetail> localList = new ArrayList<BookingDetail>();

		for (Booking b : bookingList) {

			BookingDetail bookingD = copySingleBookingToDetails(b);

			localList.add(bookingD);
		}
		return localList;
	}

	private BookingDetail copySingleBookingToDetails(Booking b) {
		BookingDetail bkDetail = new BookingDetail();
		bkDetail.setBookingId(b.getBookingId());
		bkDetail.setMemberId(b.getMember().getMemberId());
		bkDetail.setVenueId(b.getVenue().getVenueId());
		bkDetail.setDate(b.getDate());
		bkDetail.setGuestCount(b.getGuestCount());
		bkDetail.setEventName(b.getEventName());
		bkDetail.setEquipmentName(b.getEquipmentName());
		bkDetail.setFoodItemName(b.getFoodItemName());
		bkDetail.setEquipmentCost(b.getEquipmentCost());
		bkDetail.setFoodItemCost(b.getFoodItemCost());
		bkDetail.setEventCost(b.getEventCost());
		bkDetail.setTotalCost(b.getTotalCost());
		bkDetail.setPaymentStatus(b.getPaymentStatus());
		bkDetail.setFirstName(b.getMember().getFirstName());
		bkDetail.setLastName(b.getMember().getLastName());
		bkDetail.setEmail(b.getMember().getEmail());
		bkDetail.setPhoneNumber(b.getMember().getPhoneNumber());
		bkDetail.setVenueName(b.getVenue().getVenueName());
		bkDetail.setVenuePlace(b.getVenue().getVenuePlace());
		bkDetail.setVenueContact(b.getVenue().getVenueContact());
		bkDetail.setDelStatus(b.getDelStatus());
		return bkDetail;
	}

	// Service to get list of bookings for USER//####
	@Override
	public List<BookingDetail> getBookingsByUserId(int userId) {

		Member member = memberRepo.findById(userId).get();
		List<Booking> bookingList = bookingRepo.findByMember(member);

		return copyDataFromBookingToBookingDetail(bookingList);
	}

	// Service to get Booking Detail by Booking ID//####
	@Override
	public BookingDetail bookingDetail(int bookingId) {
		Booking booking = bookingRepo.findById(bookingId).get();
		return copySingleBookingToDetails(booking);
	}

	// Service to Make payment request//####
	@Override
	public int payment(int bookingId) {

		// Check if the booking for which payment is being made is already booked by any
		// other user
		// If that booking is available for payment then transfer request to add payment
		// data and return 1
		// Generate notification for the Payment.
		if (isAvailableForPayment(bookingId)) {

			Booking booking = bookingRepo.findById(bookingId).get();
			booking.setPaymentStatus("Processed");
			booking.setDelStatus(111);

			bookingRepo.save(booking);

			return 1;
		} else {
			return 0;
		}
	}

	// Service to delete Booking//####
	@Override
	public int deleteBooking(@PathVariable("bookingId") int bookingId) {
		bookingRepo.deleteById(bookingId);
		return 1;
	}

	// Check Active Bookings Are present or not.//****
	@Override
	public int checkActiveBookings(int venueId) {
		int i = 0;

		Venue venue = venueRepo.findById(venueId).get();
		List<Booking> bookings = bookingRepo.findByVenue(venue);

		List<BookingDetail> localList = copyDataFromBookingToBookingDetail(bookings);
		for (BookingDetail bd : localList) {
			LocalDate todayDate = LocalDate.now();
			LocalDate bookingDate = sqlToLocalDateConverter(bd.getDate());
			String paymentStatus = bd.getPaymentStatus();
			if ((todayDate.isBefore(bookingDate) || todayDate.isEqual(bookingDate))
					&& paymentStatus.equals("Processed")) {
				i = 1;
			}
		}
		return i;
	}

}
