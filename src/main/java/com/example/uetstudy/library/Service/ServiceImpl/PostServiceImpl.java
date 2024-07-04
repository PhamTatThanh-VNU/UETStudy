package com.example.uetstudy.library.Service.ServiceImpl;


import com.example.uetstudy.library.DTO.CommentDTO;
import com.example.uetstudy.library.DTO.PostAndCommentsDTO;
import com.example.uetstudy.library.DTO.PostDTO;
import com.example.uetstudy.library.Model.Note;
import com.example.uetstudy.library.Model.Post;
import com.example.uetstudy.library.Repository.CommentRepository;
import com.example.uetstudy.library.Repository.PostRepository;
import com.example.uetstudy.library.Service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    @Autowired
    private final PostRepository postRepository;

    @Override
    public Post save(Post post) {
        Post savePost = new Post();
        savePost.setPostTopic(post.getPostTopic());
        savePost.setPostContent(post.getPostContent());
        savePost.setStudent(post.getStudent());
        savePost.setDateCreated(post.getDateCreated());
        return postRepository.save(savePost);
    }
    @Override
    public PostAndCommentsDTO findPostDTOById(Long id) {
        PostDTO postDTO = postRepository.findPostById(id);
        List<CommentDTO> commentDTOs = postRepository.findCommentsByPostId(postDTO.getPostId());
        return new PostAndCommentsDTO(postDTO, commentDTOs);
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
    public Optional<Post> findPostById(Long id) {
        return postRepository.findById(id);
    }

    @Override
    public Post updatePostById(Post post) {
        Post postUpdate = postRepository.getReferenceById(post.getPostId());
        postUpdate.setPostTopic(post.getPostTopic());
        postUpdate.setPostContent(post.getPostContent());
        postUpdate.setStudent(post.getStudent());
        return postRepository.save(postUpdate);
    }
}
