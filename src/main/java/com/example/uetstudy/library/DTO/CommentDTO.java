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
public class CommentDTO {
    private Long cmtId;
    private String cmtContent;
    private Timestamp dateCreated;
    private Student student;
    private Post post;

    public CommentDTO(Long cmtId, String cmtContent, Timestamp dateCreated) {
        this.cmtId = cmtId;
        this.cmtContent = cmtContent;
        this.dateCreated = dateCreated;
    }
}
