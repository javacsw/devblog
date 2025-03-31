package org.example.backend.Controller;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.example.backend.Entity.pojp.Blog;
import org.example.backend.Entity.pojp.RestBean;
import org.example.backend.Entity.pojp.User;
import org.example.backend.Entity.vo.BlogVO;
import org.example.backend.Service.BlogService;

import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/blog")
public class BlogController {
    @Resource
    BlogService blogService;

    @PostMapping("addNew")
    public RestBean<String> addNewBlog(
            HttpServletRequest request,
            @RequestParam("title") String title,
            @RequestParam("content") String content
    ) {

        if (blogService.addNewBlog(new Blog(title, (Integer)request.getAttribute("id"), new Date(), content)) > 0)
            return RestBean.success("发布成功～");
        else return RestBean.failure(503, "出现错误，请联系管理员");
    }

    @GetMapping("getInroduce")
    public RestBean<List<BlogVO>> getInroduceBlog(){
        return RestBean.success("获取成功",blogService.getOrderByBlogs());
    }

    @GetMapping("getBlog")
    public RestBean<BlogVO> getBlog(@RequestParam("id")int id,
                                    HttpServletRequest request){
        Integer userId =(Integer) request.getAttribute("id");
        return RestBean.success("成功",blogService.getBlogById(id,userId));
    }

    @PostMapping("like")
    public RestBean<String> likeBlog(@RequestParam("id") int id,
                                     HttpServletRequest request) {
        int userId = (Integer) request.getAttribute("id");
        String s = blogService.doNewLike(userId, id);
        if (s == null) return RestBean.success("点赞成功！");
        else return RestBean.failure(500, s);
    }

    @PostMapping("unlike")
    public RestBean<String> unlikeBlog(@RequestParam("id")int id,
                                       HttpServletRequest request){
        int userId = (Integer) request.getAttribute("id");
        String s = blogService.deleteLike(userId, id);
        if (s == null) return RestBean.success("取消点赞成功！");
        else return RestBean.failure(500, s);
    }

    @GetMapping("check")
    public RestBean<Integer> checkLikedBlog(@RequestParam("id")int id,
                                            HttpServletRequest request){
        int userId=(Integer) request.getAttribute("id");
        return RestBean.success("",blogService.checkUserLikedBlog(userId,id));
    }

}