package com.example.uetstudy.library.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class PostAndCommentsDTO {
    private PostDTO post;
    private List<CommentDTO> comments;
}
