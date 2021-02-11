package com.programming.techie.springredditclone.mapper;

import com.programming.techie.springredditclone.dto.SubredditDto;
import com.programming.techie.springredditclone.model.Post;
import com.programming.techie.springredditclone.model.Subreddit;
import org.springframework.stereotype.Component;
import java.time.Instant;
import java.util.List;

@Component
public class SubredditMapper {
	
    public SubredditDto mapSubredditToDto(Subreddit subreddit) {
    	SubredditDto subReddditDto = new SubredditDto();
    	subReddditDto.setName(subreddit.getName());
    	subReddditDto.setDescription(subreddit.getDescription());
    	subReddditDto.setId(subreddit.getId());
    	subReddditDto.setNumberOfPosts(mapPosts(subreddit.getPosts()));
    	return subReddditDto;
    }

    private Integer mapPosts(List<Post> numberOfPosts) {
        return numberOfPosts.size();
    }

    public Subreddit mapDtoToSubreddit(SubredditDto subredditDto) {
    	Subreddit subreddit = new Subreddit();
    	subreddit.setName(subredditDto.getName());
    	subreddit.setId(subredditDto.getId());
    	subreddit.setDescription(subredditDto.getDescription());
    	subreddit.setCreatedDate(Instant.now());
    	//subreddit.setUser(user);
    	return subreddit;	
    }
}
