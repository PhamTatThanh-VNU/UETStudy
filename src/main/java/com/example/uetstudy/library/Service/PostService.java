package com.example.uetstudy.library.Service;

import com.example.uetstudy.library.DTO.PostAndCommentsDTO;
import com.example.uetstudy.library.DTO.PostDTO;
import com.example.uetstudy.library.Model.Department;
import com.example.uetstudy.library.Model.Post;

import java.util.List;
import java.util.Optional;

public interface PostService {
    Post save(Post post);
    List<Post> findAll();
    Optional<Post> findPostById(Long id);
    PostAndCommentsDTO findPostDTOById(Long id);
    void deletePostById(Long id);
    Post updatePostById(Post post);


}
