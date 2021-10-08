package com.entertain.repositories.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.entertain.dto.VideoLikedInfo;
import com.entertain.repositories.AbstractRepository;
import com.entertain.repositories.IStatsRepository;

public class StatsRepositoryImpl extends AbstractRepository<VideoLikedInfo> implements IStatsRepository{

	@Override
	public List<VideoLikedInfo> findVideoLikedInfo() {
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT v.id, v.title, v.href, SUM(h.isLiked) as totalLike ");
		sql.append("FROM video v left join history h on v.id = h.videoId ");
		sql.append("WHERE v.isActive = 1 ");
		sql.append("GROUP BY v.id, v.title, v.href ");
		sql.append("ORDER BY SUM(h.isLiked) desc");
		
		// native query nen chi tra ve 1 object chu ko ro 1 kieu cu the
		List<Object[]> objects = super.findMantByNativeQuery(sql.toString());
		
		//map data
		List<VideoLikedInfo> result = new ArrayList<>();
		objects.forEach(object -> {
			VideoLikedInfo likedInfo = getVideoLikedInfo(object);
			result.add(likedInfo);
		});
		return result;
	}
	// map dto
	private VideoLikedInfo getVideoLikedInfo(Object[] object) {
		VideoLikedInfo likedInfo = new VideoLikedInfo();
		likedInfo.setVideoId((Integer) object[0]);
		likedInfo.setTitle((String) object[1]);
		likedInfo.setHref((String) object[2]); 
		
		//set truong hop ko co luot like nao
		likedInfo.setTotalLike((BigDecimal) object[3]);
		return likedInfo;
		
	}

}
