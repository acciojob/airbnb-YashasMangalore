package com.driver.Repository;

import com.driver.model.Hotel;
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
