package com.driver1.Repository;

import com.driver1.model.Booking;
import java.util.HashMap;
import java.util.Map;

public class BookingRepository
{
    Map<String, Booking> bookingMap=new HashMap<>();

    public Map<String, Booking> getBookingMap()
    {
        return bookingMap;
    }

    public void setBookingMap(Map<String, Booking> bookingMap)
    {
        this.bookingMap = bookingMap;
    }
}
