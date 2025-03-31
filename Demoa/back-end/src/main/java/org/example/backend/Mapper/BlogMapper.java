package org.example.backend.Mapper;

import org.apache.ibatis.annotations.*;
import org.example.backend.Entity.pojp.Blog;

import java.util.List;


@Mapper
public interface BlogMapper {
    @Insert("INSERT INTO tb_blogs(title,authorId,updateDate,content)VALUES (#{title},#{authorId},#{updateDate},#{content})")
    @Options(useGeneratedKeys = true,keyProperty = "id")
    int insertNewBlog(Blog blog);

    @Select("SELECT id,title,substring(content,1,200) AS content,authorId FROM tb_blogs ORDER BY id DESC LIMIT 50")
    List<Blog> getBlogsOrderById();

    @Select("SELECT * FROM tb_blogs WHERE id=#{id}")
    Blog getBlogById(int id);

    @Update("UPDATE tb_blogs SET watches =watches +1 WHERE id=#{id}")
    int updateWatchesById(int id);

    @Update("Update tb_blogs SET likes=likes+1 WHERE id=#{id}")
    int addLikesById(int id);

    @Update("Update tb_blogs SET likes=likes-1 WHERE id=#{id}")
    int unlikeById(int id);

}
