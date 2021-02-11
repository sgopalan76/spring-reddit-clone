package com.programming.techie.springredditclone.service;

import com.programming.techie.springredditclone.dto.CommentsDto;
import com.programming.techie.springredditclone.exceptions.PostNotFoundException;
import com.programming.techie.springredditclone.mapper.CommentMapper;
import com.programming.techie.springredditclone.model.Comment;
import com.programming.techie.springredditclone.model.NotificationEmail;
import com.programming.techie.springredditclone.model.Post;
import com.programming.techie.springredditclone.model.User;
import com.programming.techie.springredditclone.repository.CommentRepository;
import com.programming.techie.springredditclone.repository.PostRepository;
import com.programming.techie.springredditclone.repository.UserRepository;
import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import java.util.List;
import static java.util.stream.Collectors.toList;

@Service
public class CommentService {
    private static final String POST_URL = "";
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthService authService;
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private MailContentBuilder mailContentBuilder;
    @Autowired
    private MailService mailService;

    public void save(CommentsDto commentsDto) {
        Post post = postRepository.findById(commentsDto.getPostId())
                .orElseThrow(() -> new PostNotFoundException(commentsDto.getPostId().toString()));
        Comment comment = commentMapper.map(commentsDto, post, authService.getCurrentUser());
        commentRepository.save(comment);

        String message = mailContentBuilder.build(authService.getCurrentUser() + " posted a comment on your post." + POST_URL);
        sendCommentNotification(message, post.getUser());
    }

    private void sendCommentNotification(String message, User user) {
        mailService.sendMail(new NotificationEmail(user.getUsername() + " Commented on your post", user.getEmail(), message));
    }

    public List<CommentsDto> getAllCommentsForPost(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new PostNotFoundException(postId.toString()));
        return commentRepository.findByPost(post)
                .stream()
                .map(commentMapper::mapToDto).collect(toList());
    }

    public List<CommentsDto> getAllCommentsForUser(String userName) {
        User user = userRepository.findByUsername(userName)
                .orElseThrow(() -> new UsernameNotFoundException(userName));
        return commentRepository.findAllByUser(user)
                .stream()
                .map(commentMapper::mapToDto)
                .collect(toList());
    }
}
