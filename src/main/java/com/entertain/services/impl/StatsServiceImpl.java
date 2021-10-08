package com.entertain.services.impl;

import java.util.List;

import com.entertain.dto.VideoLikedInfo;
import com.entertain.repositories.IStatsRepository;
import com.entertain.repositories.impl.StatsRepositoryImpl;
import com.entertain.services.IStatsService;

public class StatsServiceImpl implements IStatsService{
	
	private IStatsRepository statsRepo = new StatsRepositoryImpl();

	@Override
	public List<VideoLikedInfo> findVideoLikedInfo() {
		return statsRepo.findVideoLikedInfo();
	}

}
