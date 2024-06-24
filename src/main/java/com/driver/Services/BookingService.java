package com.driver.Services;

import com.driver.Repository.BookingRepository;
import com.driver.Repository.HotelRepository;
import com.driver.Repository.UserRepository;
import com.driver.model.Booking;
import com.driver.model.Hotel;
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
        Map<String,Booking> bookingMap=new HashMap<>();
        Hotel hotel=hotelRepository.getHotelMap().get(booking.getHotelName());
        if(hotel.getAvailableRooms()<booking.getNoOfRooms())
        {
            return -1;
        }
        String bookingId=String.valueOf(UUID.randomUUID());
        booking.setBookingId(bookingId);
        booking.setAmountToBePaid( booking.getNoOfRooms()* hotel.getPricePerNight() );

        bookingMap.put(bookingId,booking);
        bookingRepository.setBookingMap(bookingMap);
        return booking.getAmountToBePaid();
    }

    public int getBookings(Integer aadharCard)
    {
        String user=userRepository.getUserMap().get(aadharCard).getName();
        Map<String,Booking> bookingMap=bookingRepository.getBookingMap();
        int count = 0;
        if(user.isEmpty())
            return count;
        for(Map.Entry<String, Booking> bookingEntry:bookingMap.entrySet())
        {
            Booking booking=bookingEntry.getValue();
            if(booking.getBookingPersonName().equals(user))
                count++;
        }
        return count;
    }
}
