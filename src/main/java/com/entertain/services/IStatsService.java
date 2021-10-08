package com.entertain.services;

import java.util.List;

import com.entertain.dto.VideoLikedInfo;

public interface IStatsService {
	List<VideoLikedInfo> findVideoLikedInfo();
}
