package com.driver1.Repository;

import com.driver1.model.User;
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
