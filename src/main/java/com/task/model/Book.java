package com.task.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)	
	private Long bookId;
	private String price;
	private String side;
	private String size;
	private String market;
	@OneToMany(mappedBy="data", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	private Set<Channel> channel;
}
