package com.programming.techie.springredditclone.mapper;

import com.programming.techie.springredditclone.dto.CommentsDto;
import com.programming.techie.springredditclone.model.Comment;
import com.programming.techie.springredditclone.model.Post;
import com.programming.techie.springredditclone.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public class CommentMapper {
    
    public Comment map(CommentsDto commentsDto, Post post, User user) {
    	Comment comment = new Comment();
    	comment.setPost(post);
    	comment.setUser(user);
    	comment.setCreatedDate(java.time.Instant.now());
    	comment.setId(commentsDto.getId());
    	comment.setText(commentsDto.getText());
    	return comment;
    }
    
    public CommentsDto mapToDto(Comment comment) {
    	CommentsDto commentDto = new CommentsDto();
    	commentDto.setPostId(comment.getPost().getPostId());
    	commentDto.setUserName(comment.getUser().getUsername());
    	return commentDto;
    }
}