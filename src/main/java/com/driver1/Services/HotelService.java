package com.driver.Services;

import com.driver.Repository.HotelRepository;
import com.driver.model.Facility;
import com.driver.model.Hotel;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Map;

@Service
public class HotelService
{
    HotelRepository hotelRepository =new HotelRepository();

    public String addHotel(Hotel hotel)
    {
        Map<String, Hotel> hotelMap= hotelRepository.getHotelMap();

        if(hotel==null || hotel.getHotelName()==null || ObjectUtils.isEmpty(hotel) || hotelMap.containsKey(hotel.getHotelName()) )
        {
            return "FAILURE";
        }
        hotelMap.put(hotel.getHotelName(),hotel);
        hotelRepository.setHotelMap(hotelMap);
        return "SUCCESS";
    }

    public String getHotelWithMostFacilities()
    {
        Map<String,Hotel> hotelMap=hotelRepository.getHotelMap();
        String hotelNameOfMostFacilities="";
        if(hotelMap.isEmpty())
            return hotelNameOfMostFacilities;
        int maxFacilities=0;

        for(Map.Entry<String,Hotel> entry:hotelMap.entrySet())
        {
            String curHotelName = entry.getKey();
            Hotel curHotel = entry.getValue();
            int facilitiesCount = curHotel.getFacilities().size();

            if (facilitiesCount > maxFacilities)
            {
                maxFacilities = facilitiesCount;
                hotelNameOfMostFacilities = curHotelName;
            }
            else if (facilitiesCount == maxFacilities)
            {
                if (curHotelName.compareTo(hotelNameOfMostFacilities) < 0)
                {
                    hotelNameOfMostFacilities = curHotelName;
                }
            }
        }
        return hotelNameOfMostFacilities;
    }

    public Hotel updateFacilities(List<Facility> newFacilities, String hotelName)
    {
        Hotel hotel=hotelRepository.getHotelMap().get(hotelName);
        List<Facility> currentFacilities=hotel.getFacilities();

        for(Facility newFacility:newFacilities)
        {
            if(!currentFacilities.contains(newFacility))
            {
                currentFacilities.add(newFacility);
            }
        }

        hotel.setFacilities(currentFacilities);
        hotelRepository.getHotelMap().put(hotelName,hotel);
        return hotel;

    }
}
