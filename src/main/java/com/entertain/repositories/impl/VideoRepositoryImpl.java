package com.entertain.repositories.impl;

import java.util.List;

import com.entertain.entities.Video;
import com.entertain.repositories.AbstractRepository;
import com.entertain.repositories.IVideoRepository;

public class VideoRepositoryImpl extends AbstractRepository<Video> implements IVideoRepository{

	@Override
	public Video findById(Integer id) {
		return super.findById(Video.class, id);
	}

	@Override
	public Video findByHref(String href) {
		String hql = "SELECT o FROM Video o WHERE o.href = ?0";
		
		return super.findOne(Video.class, hql, href);
	}

	@Override
	public List<Video> findAll() {
		return super.findAll(Video.class, false);
	}

	@Override
	public List<Video> findAll(int pageNumber, int pageSize) {
		return super.findAll(Video.class, false, pageNumber, pageSize);
	}

	@Override
	public Video delete(Video video) {
		return super.delete(video);
	}

}
