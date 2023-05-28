package com.hub.Service;


import com.hub.dto.CommentsDto;
import com.hub.exceptions.PostNotFoundException;
import com.hub.exceptions.SpringHubException;
import com.hub.mapper.CommentMapper;
import com.hub.model.Comment;
import com.hub.model.NotificationEmail;
import com.hub.model.Post;
import com.hub.model.User;
import com.hub.repository.CommentRepository;
import com.hub.repository.PostRepository;
import com.hub.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@AllArgsConstructor

public class CommentService {
    private static final String POST_URL = "";
    private PostRepository postRepository;
    private UserRepository userRepository;
    private AuthService authService;
    private CommentMapper commentMapper;
    private CommentRepository commentRepository;
    private MailContentBuilder mailContentBuilder;
    private MailService mailService;

    public void save(CommentsDto commentsDto) {
        Post post = postRepository.findById(commentsDto.getPostId())
                .orElseThrow(() -> new PostNotFoundException(commentsDto.getPostId().toString()));
        Comment comment = commentMapper.map(commentsDto, post, authService.getCurrentUser());
        commentRepository.save(comment);

        //String message = mailContentBuilder.build(post.getUser().getUsername() + " posted a comment on your post." + POST_URL);
       // sendCommentNotification(message, post.getUser());
    }

    private void sendCommentNotification(String message, User user) {
        mailService.sendMail(new NotificationEmail(user.getUsername() + " Commented on your post", user.getEmail(), message));
    }

    public List<CommentsDto> getAllCommentsForPost(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new PostNotFoundException(postId.toString()));
        return commentRepository.findByPost(post)
                .stream()
                .map(commentMapper::mapToDto).toList();
    }

    public List<CommentsDto> getAllCommentsForUser(String userName) {
        User user = userRepository.findByUsername(userName)
                .orElseThrow(() -> new UsernameNotFoundException(userName));
        return commentRepository.findAllByUser(user)
                .stream()
                .map(commentMapper::mapToDto)
                .toList();
    }

    public boolean containsSwearWords(String comment) {
        if (comment.contains("shit")) {
            throw new SpringHubException("Comments contains unacceptable language");
        }
        return false;
    }
}
