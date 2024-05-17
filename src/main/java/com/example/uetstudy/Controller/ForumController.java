package com.example.uetstudy.Controller;

import com.example.uetstudy.library.Model.Comment;
import com.example.uetstudy.library.Model.Post;
import com.example.uetstudy.library.Service.CommentService;
import com.example.uetstudy.library.Service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
public class ForumController {

    @Autowired
    private PostService postService;
    @Autowired
    private CommentService commentService;

    @GetMapping("/delete-post")
    public String deletePost(Long id) {
        postService.deletePostById(id);
        return "redirect:/blog-single";
    }

    @PostMapping("/update-post/{id}")
    public String updatePost(@PathVariable Long id, Long noteId, @ModelAttribute("post") Post post) {
        post.setPostId(id);
        postService.updatePostById(id, post);
        return "redirect:/blog-single";
    }

    @GetMapping("/delete-cmt")
    public String deleteCmt(Long id) {
        commentService.deleteCommentById(id);
        return "redirect:/blog-single";
    }

    @PostMapping("/update-cmt/{id}")
    public String updateCmt(@PathVariable Long id, Long cmtId, @ModelAttribute("post") Comment cmt) {
        commentService.updateCommentById(id, cmt);
        return "redirect:/blog-single";
    }


}
