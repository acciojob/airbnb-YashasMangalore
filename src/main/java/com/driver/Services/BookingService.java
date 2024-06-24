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
        if (booking == null || booking.getHotelName() == null)
        {
            return -1; // or handle null booking or hotelName
        }
//        if (hotelRepository == null || hotelRepository.getHotelMap() == null)
//        {
//            return -1; // or handle null hotelRepository or hotelMap
//        }

        Hotel hotel=hotelRepository.getHotelMap().get(booking.getHotelName());
        if(hotel==null||hotel.getAvailableRooms()<booking.getNoOfRooms())
        {
            return -1;
        }
        String bookingId=String.valueOf(UUID.randomUUID());
        booking.setBookingId(bookingId);
        booking.setAmountToBePaid( booking.getNoOfRooms()* hotel.getPricePerNight() );

        Map<String, Booking> bookingMap = bookingRepository.getBookingMap();
        if (bookingMap == null)
        {
            bookingMap = new HashMap<>();
        }
        bookingMap.put(bookingId,booking);
        bookingRepository.setBookingMap(bookingMap);
        return booking.getAmountToBePaid();
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
