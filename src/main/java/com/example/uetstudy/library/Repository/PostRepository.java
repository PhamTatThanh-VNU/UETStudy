package com.example.uetstudy.library.Repository;


import com.example.uetstudy.library.DTO.CommentDTO;
import com.example.uetstudy.library.DTO.PostAndCommentsDTO;
import com.example.uetstudy.library.DTO.PostDTO;
import com.example.uetstudy.library.Model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post,Long> {
    @Query("SELECT p FROM Post p ORDER BY p.dateCreated DESC")
    List<Post> findAll();
    @Query("SELECT new com.example.uetstudy.library.DTO.PostDTO(p.postId,p.postTopic,p.postContent,p.dateCreated,p.student) from Post p where p.postId = :id")
    PostDTO findPostById(Long id);

    @Query("SELECT new com.example.uetstudy.library.DTO.CommentDTO(cmt.cmtId,cmt.cmtContent,cmt.dateCreated,cmt.student,cmt.post) " +
            "FROM Comment cmt WHERE cmt.post.postId = :postId order by cmt.dateCreated desc")
    List<CommentDTO> findCommentsByPostId(Long postId);

}
