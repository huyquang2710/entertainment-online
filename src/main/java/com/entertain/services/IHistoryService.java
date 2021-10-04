package com.entertain.services;

import java.util.List;

import com.entertain.entities.History;
import com.entertain.entities.User;
import com.entertain.entities.Video;

public interface IHistoryService {
	List<History> findByUser(String username);
	List<History> findByUserAndIsLiked(String username);
	History findByUserIdAndVideoId(Integer userId, Integer videoId);
	History create(User user, Video video);
	
	//localhost:8080/video?action=watch
	//localhost:8080/video?action=like&href=?
	Boolean updateLikeOrUnlike(User user, String href);
}
