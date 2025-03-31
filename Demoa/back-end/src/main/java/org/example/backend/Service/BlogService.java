package org.example.backend.Service;

import org.example.backend.Entity.pojp.Blog;
import org.example.backend.Entity.vo.BlogVO;

import java.util.List;

public interface BlogService {
   int addNewBlog(Blog blog);
   List<BlogVO> getOrderByBlogs();
   BlogVO getBlogById(int id,int userId);

   String doNewLike(Integer userId,Integer blogId);

   String deleteLike(Integer userId, Integer blogId);

   int checkUserLikedBlog(Integer userId,Integer blogId);
}
