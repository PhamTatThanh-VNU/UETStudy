package com.example.uetstudy.Controller;

import com.example.uetstudy.library.DTO.CommentDTO;
import com.example.uetstudy.library.DTO.PostAndCommentsDTO;
import com.example.uetstudy.library.DTO.PostDTO;
import com.example.uetstudy.library.Model.Comment;
import com.example.uetstudy.library.Model.Document;
import com.example.uetstudy.library.Model.Post;
import com.example.uetstudy.library.Model.Student;
import com.example.uetstudy.library.Service.CommentService;
import com.example.uetstudy.library.Service.PostService;
import com.example.uetstudy.library.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class ForumController {
    @Autowired
    private StudentService studentService;

    @Autowired
    private PostService postService;
    @Autowired
    private CommentService commentService;
    @PostMapping("/new-post")
    public String save(Post post) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        try {
            Student student = studentService.findByUserName(authentication.getName());
            post.setStudent(student);
            postService.save(post);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/blog-single";
    }
    @GetMapping("/delete-post")
    public String deletePost(Long id) {
        postService.deletePostById(id);
        return "redirect:/blog-single";
    }
    @GetMapping("/findPostById")
    @ResponseBody
    public Optional<Post> findPostById(Long id){
        return postService.findPostById(id);
    }
    @GetMapping("/findPostDTOById")
    @ResponseBody
    public PostAndCommentsDTO findPostDTOById(Long id){
        return postService.findPostDTOById(id);
    }
    @PostMapping("/update-post")
    public String updatePost(Post post) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        try {
            Student student = studentService.findByUserName(authentication.getName());
            post.setStudent(student);
            postService.updatePostById(post);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/blog-single";
    }
    @PostMapping("/reply-post/postId={id}")
    public ResponseEntity<String> save(@PathVariable Long id, @RequestParam("message") String message) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Comment comment = new Comment();
        Student student = studentService.findByUserName(authentication.getName());
        Optional<Post> post = postService.findPostById(id);
        Post postReply = post.get();
        comment.setStudent(student);
        comment.setPost(postReply);
        comment.setCmtContent(message);
        commentService.save(comment);
        return  new ResponseEntity<>("Reply submitted successfully", HttpStatus.OK);
    }
    @GetMapping("/get-comments/postId={id}")
    @ResponseBody
    public List<CommentDTO> getComment(@PathVariable Long id){
        return commentService.findCommentByPostId(id);
    }
    @GetMapping("/delete-cmt")
    @ResponseBody
    public void deleteCmt(Long id) {
        commentService.deleteCommentById(id);
    }

    @PostMapping("/update-cmt/{id}")
    public String updateCmt(@PathVariable Long id, Long cmtId, @ModelAttribute("post") Comment cmt) {
        commentService.updateCommentById(id, cmt);
        return "redirect:/blog-single";
    }


}
