package com.entertain.services.impl;

import java.util.List;

import com.entertain.entities.Video;
import com.entertain.repositories.IVideoRepository;
import com.entertain.repositories.impl.VideoRepositoryImpl;
import com.entertain.services.IVideoService;

public class VideoServiceImpl implements IVideoService{
	
	private IVideoRepository userRepo;
	
	public VideoServiceImpl() {
		userRepo = new VideoRepositoryImpl();
	}

	@Override
	public Video findById(Integer id) {
		return userRepo.findById(id);
	}

	@Override
	public Video findByHref(String href) {
		return userRepo.findByHref(href);
	}

	@Override
	public List<Video> findAll() {
		return userRepo.findAll();
	}

	@Override
	public List<Video> findAll(int pageNumber, int pageSize) {
		return userRepo.findAll(pageNumber, pageSize);
	}

	@Override
	public Video create(Video video) {
		video.setIsActive(Boolean.TRUE);
		video.setViews(0);
		video.setShares(0);
		return userRepo.create(video);
	}

	@Override
	public Video update(Video video) {
		return userRepo.update(video);
	}

	@Override
	public Video delete(String href) {
		Video entity = userRepo.findByHref(href);
		entity.setIsActive(Boolean.FALSE);
		return update(entity);
	}

}
