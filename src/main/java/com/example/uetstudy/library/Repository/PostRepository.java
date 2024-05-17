package com.example.uetstudy.library.Repository;


import com.example.uetstudy.library.DTO.PostDTO;
import com.example.uetstudy.library.Model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post,Long> {
    @Query("SELECT new com.example.uetstudy.library.DTO.PostDTO(pt.postId,pt.postTopic,pt.postContent,pt.dateCreated,pt.comments) " +
            "FROM Post pt WHERE pt.student.studentId = :studentId")
    List<PostDTO> findPostByStudentId(Long studentId);

}
