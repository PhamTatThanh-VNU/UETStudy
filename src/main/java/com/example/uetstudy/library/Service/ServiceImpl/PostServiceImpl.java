package com.example.uetstudy.library.Service.ServiceImpl;


import com.example.uetstudy.library.DTO.PostDTO;
import com.example.uetstudy.library.Model.Note;
import com.example.uetstudy.library.Model.Post;
import com.example.uetstudy.library.Repository.PostRepository;
import com.example.uetstudy.library.Service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    @Autowired
    private final PostRepository postRepository;

    @Override
    public List<PostDTO> findPostByStudentId(Long studentId) {
        return postRepository.findPostByStudentId(studentId);
    }

    @Override
    public List<Post> findAll() {
        return postRepository.findAll();
    }

    @Override
    public void deletePostById(Long id) {
        postRepository.deleteById(id);
    }

    @Override
    public Post updatePostById(Long id, Post post) {
        Post postUpdate = postRepository.getReferenceById(id);
        postUpdate.setPostTopic(post.getPostTopic());
        postUpdate.setPostContent(post.getPostContent());
        return postRepository.save(postUpdate);
    }
}
