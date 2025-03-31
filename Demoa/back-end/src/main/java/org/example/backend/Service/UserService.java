package org.example.backend.Service;

import org.example.backend.Entity.pojp.NotificationQueryObject;
import org.example.backend.Entity.pojp.User;

import java.util.List;

public interface UserService {
    User login(String username, String password);
    User getUserById(int id);
    int getUnreadNotificationCount(int userId);
    List<NotificationQueryObject> getAllUsersNotificationByType(Integer userId,Integer type);
    String readNotificationById(Integer userId,int id);
    String clearNotificationByType(Integer userId,Integer type);
}
