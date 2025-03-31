package org.example.backend.Service.Impl;

import jakarta.annotation.Resource;
import org.example.backend.Entity.pojp.Notification;
import org.example.backend.Entity.pojp.NotificationQueryObject;
import org.example.backend.Entity.pojp.User;
import org.example.backend.Mapper.NotificationMapper;
import org.example.backend.Mapper.UserMapper;
import org.example.backend.Service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class UserServicelmpl implements UserService {
    @Resource
    UserMapper userMapper;
    @Resource
    NotificationMapper notificationMapper;
    @Override
    public User login(String username, String password) {
        return userMapper.getUserByUsernameAndPassword(username,password);
    }

    @Override
    public User getUserById(int id) {
        return userMapper.getUserById(id);
    }

    @Override
    public int getUnreadNotificationCount(int userId) {
        return notificationMapper.getUnreadNotificationCountByUserId(userId);
    }

    @Override
    public List<NotificationQueryObject> getAllUsersNotificationByType(Integer userId,Integer type) {
        return notificationMapper.getAllNotificationByUserIdAndType(userId,type);
    }



    @Override
    public String readNotificationById(Integer userId,int id) {
        if(notificationMapper.updateIsReadByRSId(id,1,userId)==1)return null; else return "出现错误，请稍后重试";
    }

    @Override
    public String clearNotificationByType(Integer userId, Integer type) {
        if(notificationMapper.updateIsReadByUserId(type,userId)>0) return null;else return "出现错误，请稍后重试";
    }


}
