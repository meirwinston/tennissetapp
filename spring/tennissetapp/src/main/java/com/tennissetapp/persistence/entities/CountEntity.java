package com.tennissetapp.persistence.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class CountEntity{
	@Id
	protected Long count;

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}
	
	
}
