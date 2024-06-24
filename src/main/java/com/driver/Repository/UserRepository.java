package com.driver.Repository;

import com.driver.model.User;
import java.util.HashMap;
import java.util.Map;

public class UserRepository
{
    Map<Integer, User> userMap=new HashMap<>();

    public Map<Integer, User> getUserMap()
    {
        return userMap;
    }

    public void setUserMap(Map<Integer, User> userMap)
    {
        this.userMap = userMap;
    }
}
