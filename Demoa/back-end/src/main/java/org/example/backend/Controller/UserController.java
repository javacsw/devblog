package org.example.backend.Controller;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.example.backend.Entity.pojp.NotificationQueryObject;
import org.example.backend.Entity.pojp.RestBean;
import org.example.backend.Entity.pojp.User;
import org.example.backend.Service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")

public class UserController {
    @Resource
    UserService userService;
    @GetMapping("myinfo")
    public RestBean<User> getMyinfo(HttpServletRequest request){
        User user=userService.getUserById((Integer)request.getAttribute("id"));
        user.setPassword(null);
        user.setUnReadNotiCnt(userService.getUnreadNotificationCount(user.getId()));
        return RestBean.success("成功",user);
    }
    @GetMapping("logout")
    public RestBean<String> logout(HttpSession session){
        User logoinUser=(User) session.getAttribute("user");
        if(logoinUser==null)return RestBean.failure(401,"您尚未登入");
        else{
            session.removeAttribute("user");
            session.invalidate();
        }
        return RestBean.success("登出成功");
    }

    @GetMapping("getNotifications")
    public RestBean<List<NotificationQueryObject>> getAllUsersNotifications(@RequestParam("type")Integer type,
                                                                            HttpServletRequest request)
    {
        Integer userId=(Integer) request.getAttribute("id");
        return RestBean.success("请求成功",userService.getAllUsersNotificationByType(userId,type));
    }

    @PostMapping("readNotification")
    public RestBean<String> readNotification(@RequestParam("id")Integer id,HttpServletRequest request){
        Integer userId=(Integer) request.getAttribute("id");
        String status=userService.readNotificationById(userId,id);
        if (status!=null) return RestBean.failure(501,status);else return RestBean.success("已读成功");
    }

    @PostMapping("clearNotification")
    public RestBean<String> clearNotification(@RequestParam("type")Integer type,HttpServletRequest request){
        Integer userId=(Integer) request.getAttribute("id");
        String status=userService.clearNotificationByType(userId,type);
        if (status!=null) return RestBean.failure(501,status);else return RestBean.success("已读成功");
    }

}
