package com.programming.techie.springredditclone.mapper;

import com.github.marlonlom.utilities.timeago.TimeAgo;
import org.springframework.stereotype.Component;
import com.programming.techie.springredditclone.dto.PostRequest;
import com.programming.techie.springredditclone.dto.PostResponse;
import com.programming.techie.springredditclone.model.*;
import com.programming.techie.springredditclone.repository.CommentRepository;
import com.programming.techie.springredditclone.repository.VoteRepository;
import com.programming.techie.springredditclone.service.AuthService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.Instant;
import java.util.Optional;

import static com.programming.techie.springredditclone.model.VoteType.DOWNVOTE;
import static com.programming.techie.springredditclone.model.VoteType.UPVOTE;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public class PostMapper {
	@Autowired
    private CommentRepository commentRepository;
	
	@Autowired
    private VoteRepository voteRepository;
	
	@Autowired
    private AuthService authService;

    public Post map(PostRequest postRequest, Subreddit subreddit, User user) {
    	Post post = new Post();
    	post.setCreatedDate(Instant.now());
    	post.setDescription(postRequest.getDescription());
    	post.setSubreddit(subreddit);
    	post.setVoteCount(0);
    	post.setUser(user);
    	return post;
    }

    public PostResponse mapToDto(Post post) {
    	PostResponse pResponse = new PostResponse();
    	pResponse.setId(post.getPostId());
    	pResponse.setSubredditName(post.getSubreddit().getName());
    	pResponse.setUserName(post.getUser().getUsername());
    	pResponse.setCommentCount(commentCount(post));
    	pResponse.setUpVote(isPostUpVoted(post));
    	pResponse.setDownVote(isPostDownVoted(post));
    	return pResponse;
    }

    Integer commentCount(Post post) {
        return commentRepository.findByPost(post).size();
    }

    String getDuration(Post post) {
        return TimeAgo.using(post.getCreatedDate().toEpochMilli());
    }

    boolean isPostUpVoted(Post post) {
        return checkVoteType(post, UPVOTE);
    }

    boolean isPostDownVoted(Post post) {
        return checkVoteType(post, DOWNVOTE);
    }

    private boolean checkVoteType(Post post, VoteType voteType) {
        if (authService.isLoggedIn()) {
            Optional<Vote> voteForPostByUser =
                    voteRepository.findTopByPostAndUserOrderByVoteIdDesc(post,
                            authService.getCurrentUser());
            return voteForPostByUser.filter(vote -> vote.getVoteType().equals(voteType))
                    .isPresent();
        }
        return false;
    }

}