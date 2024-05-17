package com.example.uetstudy.library.Service;

import com.example.uetstudy.library.DTO.PostDTO;
import com.example.uetstudy.library.Model.Department;
import com.example.uetstudy.library.Model.Post;

import java.util.List;

public interface PostService {
    List<PostDTO> findPostByStudentId(Long studentId);
    List<Post> findAll();
    void deletePostById(Long id);
    Post updatePostById(Long id, Post post);


}
