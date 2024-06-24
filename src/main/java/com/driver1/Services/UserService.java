package com.driver1.Services;

import com.driver1.Repository.UserRepository;
import com.driver1.model.User;
import org.springframework.stereotype.Service;
import java.util.Map;

@Service
public class UserService
{
    UserRepository userRepository=new UserRepository();

    public Integer addUser(User user)
    {
        Map<Integer,User> userMap=userRepository.getUserMap();
        userMap.put(user.getaadharCardNo(),user);
        userRepository.setUserMap(userMap);

        return user.getaadharCardNo();
    }
}
