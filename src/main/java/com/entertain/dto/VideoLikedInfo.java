package com.entertain.dto;

import java.math.BigDecimal;

public class VideoLikedInfo {
	private Integer videoId;
	private String title;
	private String href;
	private BigDecimal totalLike;
	
	
	
	public Integer getVideoId() {
		return videoId;
	}
	public void setVideoId(Integer videoId) {
		this.videoId = videoId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getHref() {
		return href;
	}
	public void setHref(String href) {
		this.href = href;
	}
	public BigDecimal getTotalLike() {
		return totalLike;
	}
	public void setTotalLike(BigDecimal totalLike) {
		this.totalLike = totalLike;
	}


}
