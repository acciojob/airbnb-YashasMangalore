package com.driver1.Repository;

import com.driver1.model.Hotel;
import java.util.HashMap;
import java.util.Map;

public class HotelRepository
{
    Map<String, Hotel> hotelMap=new HashMap<>();

    public Map<String, Hotel> getHotelMap()
    {
        return hotelMap;
    }

    public void setHotelMap(Map<String, Hotel> hotelMap)
    {
        this.hotelMap = hotelMap;
    }
}
