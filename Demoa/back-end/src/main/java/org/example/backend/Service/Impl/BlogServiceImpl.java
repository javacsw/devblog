package org.example.backend.Service.Impl;

import jakarta.annotation.Resource;
import lombok.Data;
import org.example.backend.Entity.pojp.Blog;
import org.example.backend.Entity.pojp.Notification;
import org.example.backend.Entity.pojp.NotificationRS;
import org.example.backend.Entity.vo.BlogVO;
import org.example.backend.Mapper.BlogMapper;
import org.example.backend.Mapper.LikeMapper;
import org.example.backend.Mapper.NotificationMapper;
import org.example.backend.Mapper.UserMapper;
import org.example.backend.Service.BlogService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service

public class BlogServiceImpl implements BlogService {
    @Resource
    BlogMapper blogMapper;
    @Resource
    LikeMapper likeMapper;
    @Resource
    UserMapper userMapper;
    @Resource
    NotificationMapper notificationMapper;

    @Override
    public int addNewBlog(Blog blog) {

        return blogMapper.insertNewBlog(blog);
    }

    @Override
    public List<BlogVO> getOrderByBlogs() {
        List<BlogVO> result =new ArrayList<>();
        for(Blog blog:blogMapper.getBlogsOrderById())
        {
            result.add(new BlogVO(blog,userMapper.getUsernameById(blog.getAuthorId()),userMapper.getAvatorById(blog.getAuthorId())));
        }
        return result;
    }

    @Override
    public BlogVO getBlogById(int id,int userId) {
        blogMapper.updateWatchesById(id);
        Blog blog=blogMapper.getBlogById(id);
        return new BlogVO(blog,userMapper.getUsernameById(blog.getAuthorId()),
                userMapper.getAvatorById(blog.getAuthorId()),
                likeMapper.checkUserLikedBlog(userId,id)!=null,
                false
                );
    }

    @Override
    public String doNewLike(Integer userId, Integer blogId) {
        if (likeMapper.checkUserLikedBlog(userId,blogId) != null) return "你已经点过赞了";
        if (likeMapper.addNewLikeRecord(userId,blogId) == 1 &&blogMapper.addLikesById(blogId )==1){
            Blog blog =blogMapper.getBlogById(blogId);
            Notification notification=new Notification(0,"点赞通知","您的文章:\""+blog.getTitle()+"\"刚刚被用户uid:"+userId+"点赞了",new Date());
           int a= notificationMapper.insertNewNotificationData(notification);
            notificationMapper.insertNewNotificationRS(new NotificationRS(blog.getAuthorId(),notification.getId(),false));
            return null;
        }else return "发生错误，请重试";
    }

    @Override
    public String deleteLike(Integer userId, Integer blogId) {
        if (likeMapper.checkUserLikedBlog(userId, blogId) == null) return "你还没有点过赞";
        if (likeMapper.deleteLikeRecord(userId, blogId) == 1 &&blogMapper.unlikeById(blogId )==1) return null;else return "发生错误，请联系管理员";
    }

    @Override
    public int checkUserLikedBlog(Integer userId, Integer blogId) {
        return likeMapper.checkUserLikedBlog(userId,blogId);
    }


}
