package com.entertain.repositories;

import java.util.List;

import com.entertain.dto.VideoLikedInfo;

public interface IStatsRepository {
	List<VideoLikedInfo> findVideoLikedInfo();
}
