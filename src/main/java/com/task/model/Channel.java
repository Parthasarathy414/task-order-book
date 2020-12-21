package com.task.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Channel {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long id;

	public String chan_name;
	public String subchan_name;
	public String type;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="bookId", nullable=false)
	private Book data;
}

