package com.example.uetstudy.library.Repository;


import com.example.uetstudy.library.DTO.CommentDTO;
import com.example.uetstudy.library.Model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {
    @Query("SELECT new com.example.uetstudy.library.DTO.CommentDTO(cmt.cmtId,cmt.cmtContent,cmt.dateCreated) " +
            "FROM Comment cmt WHERE cmt.post.postId = :postId")
    List<CommentDTO> findCommentByPostId(Long postId);

}
