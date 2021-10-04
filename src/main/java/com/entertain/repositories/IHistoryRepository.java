package com.entertain.repositories;

import java.util.List;

import com.entertain.entities.History;

public interface IHistoryRepository {
	List<History> findByUser(String username);
	List<History> findByUserAndIsLiked(String username);
	History findByUserIdAndVideoId(Integer userId, Integer videoId);
	History create(History history);
	History update(History history);
}
