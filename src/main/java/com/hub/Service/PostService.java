package com.hub.Service;


import com.hub.dto.PostRequest;
import com.hub.dto.PostResponse;
import com.hub.exceptions.PostNotFoundException;
import com.hub.exceptions.SubhubNotFoundException;
import com.hub.mapper.PostMapper;
import com.hub.model.Post;
import com.hub.model.Subhub;
import com.hub.model.User;
import com.hub.repository.PostRepository;
import com.hub.repository.SubhubRepository;
import com.hub.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@Slf4j
@Transactional
@AllArgsConstructor
public class PostService {
    private  PostRepository postRepository;
    private SubhubRepository subhubRepository;
    private  UserRepository userRepository;
    private  AuthService authService;
    private  PostMapper postMapper;

    public void save(PostRequest postRequest) {
        Subhub subhub = subhubRepository.findByName(postRequest.getSubhubName())
                .orElseThrow(() -> new SubhubNotFoundException(postRequest.getSubhubName()));
        postRepository.save(postMapper.map(postRequest, subhub, authService.getCurrentUser()));
    }

    @Transactional(readOnly = true)
    public PostResponse getPost(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new PostNotFoundException(id.toString()));
        return postMapper.mapToDto(post);
    }

    @Transactional(readOnly = true)
    public List<PostResponse> getAllPosts() {
        return postRepository.findAll()
                .stream()
                .map(postMapper::mapToDto)
                .collect(toList());
    }

    @Transactional(readOnly = true)
    public List<PostResponse> getPostsBySubreddit(Long subhubId) {
        Subhub subhub = subhubRepository.findById(subhubId)
                .orElseThrow(() -> new SubhubNotFoundException(subhubId.toString()));
        List<Post> posts = postRepository.findAllBySubhub(subhub);
        return posts.stream().map(postMapper::mapToDto).collect(toList());
    }

    @Transactional(readOnly = true)
    public List<PostResponse> getPostsByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
        return postRepository.findByUser(user)
                .stream()
                .map(postMapper::mapToDto)
                .collect(toList());
    }
}
