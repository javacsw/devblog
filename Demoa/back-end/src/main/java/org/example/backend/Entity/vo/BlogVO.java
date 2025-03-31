package org.example.backend.Entity.vo;

import lombok.Data;
import org.example.backend.Entity.pojp.Blog;

//View Object
@Data
public class BlogVO {
    private Blog blog;
    private String username,avator;
    private Boolean isLiked,isFav;

    public BlogVO(Blog blog, String username, String avator, Boolean isLiked, Boolean isFav) {
        this.blog = blog;
        this.username = username;
        this.avator = avator;
        this.isLiked = isLiked;
        this.isFav = isFav;
    }

    public BlogVO(Blog blog, String username, String avator) {
        this.blog = blog;
        this.username = username;
        this.avator = avator;
    }
}
