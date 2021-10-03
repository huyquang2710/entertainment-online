package com.entertain.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "video")
@AllArgsConstructor
@NoArgsConstructor
@Setter @Getter
public class Video {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String title;
	private String href;
	private String poster;
	private Integer views;
	private Integer shares;
	private String description;
	@Column(name = "isActive")
	private Boolean isActive;
}
