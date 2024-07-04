package com.example.uetstudy.library.Service.ServiceImpl;


import com.example.uetstudy.library.DTO.CommentDTO;
import com.example.uetstudy.library.Model.Comment;
import com.example.uetstudy.library.Repository.CommentRepository;
import com.example.uetstudy.library.Service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    @Autowired
    private final CommentRepository commentRepository;

    @Override
    public List<CommentDTO> findCommentByPostId(Long id) {
        return commentRepository.findCommentByPostId(id);
    }

    @Override
    public void deleteCommentById(Long id) {
        commentRepository.deleteById(id);
    }

    @Override
    public Comment updateCommentById(Long id, Comment comment) {
        Comment commentUpdate = commentRepository.getReferenceById(id);
        commentUpdate.setCmtContent(comment.getCmtContent());;
        return commentRepository.save(commentUpdate);
    }

    @Override
    public Comment save(Comment comment) {
        Comment newComment = new Comment();
        newComment.setCmtContent(comment.getCmtContent());
        newComment.setStudent(comment.getStudent());
        newComment.setPost(comment.getPost());
        newComment.setDateCreated(comment.getDateCreated());
        return commentRepository.save(newComment);
    }
}
