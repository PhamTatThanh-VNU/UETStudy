package com.example.uetstudy.library.Service;

import com.example.uetstudy.library.DTO.CommentDTO;

import com.example.uetstudy.library.Model.Comment;


import java.util.List;

public interface CommentService {
    List<CommentDTO> findCommentByPostId(Long postId);
    void deleteCommentById(Long id);
    Comment  updateCommentById(Long id, Comment comment);

}
