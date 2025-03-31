package org.example.backend.Mapper;

import org.apache.ibatis.annotations.*;
import org.example.backend.Entity.pojp.Notification;
import org.example.backend.Entity.pojp.NotificationQueryObject;
import org.example.backend.Entity.pojp.NotificationRS;

import java.util.List;

@Mapper
public interface NotificationMapper {
    @Insert("INSERT INTO tb_notification (title,content,type,createTime) VALUES "+"(#{title},#{content},#{type},#{createTime})")
    @Options(useGeneratedKeys = true,keyColumn = "id",keyProperty = "id")
    int insertNewNotificationData(Notification notification);
    @Insert("INSERT INTO rs_notification (id,userId,notiId,isRead) VALUES "+"(#{id},#{userId},#{notiId},#{isRead})")
    @Options(useGeneratedKeys = true,keyColumn = "id")
    int insertNewNotificationRS(NotificationRS notificationRS);
    @Select("SELECT COUNT(*) AS 'CNT' FROM rs_notification WHERE userId=#{userId} AND isRead=false;")
    int getUnreadNotificationCountByUserId(Integer userId);
    @Select("SELECT a.id,a.content,a.title,a.createTime,a.type,b.isRead \n" +
            "FROM tb_notification a\n" +
            "INNER JOIN rs_notification b ON a.id=b.notiId\n" +
            "WHERE b.userId=#{userId} AND a.type=#{type};")

    List<NotificationQueryObject> getAllNotificationByUserIdAndType(Integer userId,Integer type);

    @Update("UPDATE rs_notification SET isRead=#{isRead} WHERE id=#{id} AND userId=#{userId}")
    int updateIsReadByRSId(Integer id,Integer isRead,Integer userId);

    @Update("UPDATE rs_notification,tb_notification SET rs_notification.isRead =1 WHERE tb_notification.type=#{type} AND tb_notification.id=rs_notification.notiId ANd rs_notification.userId=#{userId}")
    int updateIsReadByUserId(Integer type,Integer userId);
}
