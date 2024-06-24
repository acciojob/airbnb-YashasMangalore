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

    public int bookARoom(Booking booking)
    {
        if (booking == null || booking.getHotelName() == null || hotelRepository == null || hotelRepository.getHotelMap() == null || userRepository == null || userRepository.getUserMap() == null) {
            return -1; // or handle null scenarios appropriately
        }
        Map<String, Hotel> hotelMap=hotelRepository.getHotelMap();
        Hotel hotel=hotelMap.get(booking.getHotelName());
        if(hotel==null)
            return -1;
        if(hotel.getAvailableRooms()>=booking.getNoOfRooms())
        {
            String bookingId=String.valueOf(UUID.randomUUID());
            booking.setBookingId(bookingId);
            booking.setAmountToBePaid( booking.getNoOfRooms() * hotel.getPricePerNight() );
            hotel.setAvailableRooms(hotel.getAvailableRooms()-booking.getNoOfRooms());
            hotelMap.put(hotel.getHotelName(),hotel);
            hotelRepository.setHotelMap(hotelMap);
            Map<String, Booking> bookingMap = bookingRepository.getBookingMap();
            bookingMap.put(bookingId,booking);
            bookingRepository.setBookingMap(bookingMap);
            return booking.getNoOfRooms() * hotel.getPricePerNight();
        }
        else
        {
            return -1;
        }
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
