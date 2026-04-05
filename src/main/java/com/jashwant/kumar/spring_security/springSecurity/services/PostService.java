package com.jashwant.kumar.spring_security.springSecurity.services;

import com.jashwant.kumar.spring_security.springSecurity.dto.PostDTO;
import java.util.List;

public interface PostService {

    List<PostDTO> getAllPosts();

    PostDTO createNewPost(PostDTO inputPost);

    PostDTO getPostById(Long postId);

    PostDTO updatePost(PostDTO inputPost, Long postId);
}
