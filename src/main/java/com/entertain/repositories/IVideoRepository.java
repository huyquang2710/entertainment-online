package com.entertain.repositories;

import java.util.List;

import com.entertain.entities.Video;

public interface IVideoRepository {
	Video findById(Integer id);
	Video findByHref(String href);
	List<Video> findAll();
	List<Video> findAll(int pageNumber, int pageSize);
	Video create(Video video);
	Video update(Video video);
	Video delete(Video video);
}
