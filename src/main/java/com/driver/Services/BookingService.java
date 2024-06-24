package com.driver.Services;

import com.driver.Repository.BookingRepository;
import com.driver.Repository.HotelRepository;
import com.driver.Repository.UserRepository;
import com.driver.model.Booking;
import com.driver.model.Hotel;
import com.driver.model.User;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class BookingService
{
    BookingRepository bookingRepository=new BookingRepository();
    HotelRepository hotelRepository=new HotelRepository();
    UserRepository userRepository=new UserRepository();

    public Integer bookARoom(Booking booking) {
        // Retrieve hotelMap from hotelRepository
        Map<String, Hotel> hotelMap = hotelRepository.getHotelMap();

        // Retrieve Hotel object using booking's hotelName
        Hotel hotel = hotelMap.get(booking.getHotelName());

        // Check if hotel exists
        if (hotel == null) {
            return -1; // Hotel not found
        }

        // Check if there are enough available rooms
        if (hotel.getAvailableRooms() < booking.getNoOfRooms()) {
            return -1; // Not enough rooms available
        }

        // Generate booking ID
        String bookingId = String.valueOf(UUID.randomUUID());
        booking.setBookingId(bookingId);

        // Calculate amount to be paid
        int amountPaid = booking.getNoOfRooms() * hotel.getPricePerNight();
        booking.setAmountToBePaid(amountPaid);

        // Update available rooms in the hotel
        hotel.setAvailableRooms(hotel.getAvailableRooms() - booking.getNoOfRooms());

        // Update booking repository
        Map<String, Booking> bookingMap = bookingRepository.getBookingMap();
        bookingMap.put(bookingId, booking);
        bookingRepository.setBookingMap(bookingMap);

        // Update hotel repository
        hotelMap.put(booking.getHotelName(), hotel);
        hotelRepository.setHotelMap(hotelMap);

        return amountPaid;
    }


    public int getBookings(Integer aadharCard)
    {
        int count = 0;
        if (userRepository == null || bookingRepository == null)
        {
            return count; // or throw an exception, depending on your design
        }
        User userDetails=userRepository.getUserMap().get(aadharCard);
        if(userDetails==null)
            return count;
        String user=userDetails.getName();
        if (user == null || user.isEmpty()) {
            return count;
        }
        Map<String,Booking> bookingMap=bookingRepository.getBookingMap();

        for(Map.Entry<String, Booking> bookingEntry:bookingMap.entrySet())
        {
            Booking booking=bookingEntry.getValue();
            if(booking.getBookingPersonName() != null||booking.getBookingPersonName().equals(user))
                count++;
        }
        return count;
    }
}
