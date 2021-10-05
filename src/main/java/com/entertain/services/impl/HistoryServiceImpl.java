package com.entertain.services.impl;

import java.sql.Timestamp;
import java.util.List;

import com.entertain.entities.History;
import com.entertain.entities.User;
import com.entertain.entities.Video;
import com.entertain.repositories.IHistoryRepository;
import com.entertain.repositories.impl.HisoryRepositoryImpl;
import com.entertain.services.IHistoryService;
import com.entertain.services.IVideoService;

public class HistoryServiceImpl implements IHistoryService{
	
	private IVideoService videoService = new VideoServiceImpl();
	
	private IHistoryRepository historyRepo;
	
	public HistoryServiceImpl() {
		historyRepo = new HisoryRepositoryImpl();
	}
	
	@Override
	public List<History> findByUser(String username) {
		return historyRepo.findByUser(username);
	}

	@Override
	public List<History> findByUserAndIsLiked(String username) {
		return historyRepo.findByUserAndIsLiked(username);
	}

	@Override
	public History findByUserIdAndVideoId(Integer userId, Integer videoId) {
		return historyRepo.findByUserIdAndVideoId(userId, videoId);
	}

	@Override
	public History create(User user, Video video) {
		History exitsHistory = findByUserIdAndVideoId(user.getId(), video.getId());
		
		if(exitsHistory == null) {
			exitsHistory = new History();
			exitsHistory.setUser(user);
			exitsHistory.setVideo(video);
			exitsHistory.setViewedDate(new Timestamp(System.currentTimeMillis()));
			exitsHistory.setIsLiked(Boolean.FALSE);
			return historyRepo.create(exitsHistory);
		}
		return exitsHistory;
	}
	
	// check like & unlike
	@Override
	public Boolean updateLikeOrUnlike(User user, String href) {
		Video video = videoService.findByHref(href); // lay video
		History existHistory = findByUserIdAndVideoId(user.getId(), video.getId());
		
		// neu user chua like thi isliked la false. --> chuyen thanh true
		if(existHistory.getIsLiked() == Boolean.FALSE) {
			existHistory.setIsLiked(Boolean.TRUE);
			existHistory.setLikedDate(new Timestamp(System.currentTimeMillis())); // lay time hien tai
		} else {
			existHistory.setIsLiked(Boolean.FALSE);
			existHistory.setLikedDate(null);
		}
		History updateHistory = historyRepo.update(existHistory);
		return updateHistory != null ? true : false;
	}

}
