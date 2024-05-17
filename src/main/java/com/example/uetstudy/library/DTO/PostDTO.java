package com.example.uetstudy.library.DTO;

import com.example.uetstudy.library.Model.Comment;
import com.example.uetstudy.library.Model.Post;
import com.example.uetstudy.library.Model.Student;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
@AllArgsConstructor
public class PostDTO {
    private Long postId;
    private String postTopic;
    private String postContent;
    private Timestamp dateCreated;
    private Student student;
    private List<Comment> comments;
    public PostDTO(Long postId, String postTopic, String postContent, Timestamp dateCreated,List<Comment> comments) {
        this.postId = postId;
        this.postTopic = postTopic;
        this.postContent = postContent;
        this.dateCreated = dateCreated;
        this.comments=comments;
    }


}
