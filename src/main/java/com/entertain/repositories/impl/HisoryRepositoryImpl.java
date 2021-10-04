package com.entertain.repositories.impl;

import java.util.List;

import com.entertain.entities.History;
import com.entertain.repositories.AbstractRepository;
import com.entertain.repositories.IHistoryRepository;

public class HisoryRepositoryImpl extends AbstractRepository<History> implements IHistoryRepository{

	@Override
	public List<History> findByUser(String username) {
		// sql: SELECT * FROM history FROM userId
		String hql = "SELECT o  FROM Hisory o WHERE o.user.username = ?0 AND o.video.isActive = 1" + " ORDER BY o.viewedDate DESC";	
		return super.findMany(History.class, hql, username);
	}

	@Override
	public List<History> findByUserAndIsLiked(String username) {
		String hql = "SELECT o FROM History o WHERE o.user.username = ?0 AND o.isLiked = 1"
				+ " AND o.video.isActive = 1"
				+ " ORDER BY o.viewedDate DESC";
		return super.findMany(History.class, hql, username);
	}

	@Override
	public History findByUserIdAndVideoId(Integer userId, Integer videoId) {
		String hql = "SELECT o FROM History o WHERE o.user.userId = ?0 AND o.video.videoId = ?1"
				+ " AND o.video.isActive = 1";
		return super.findOne(History.class, hql, userId, videoId);
	}

}
