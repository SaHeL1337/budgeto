package com.sahsec.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Category {

	@Id
	@Column(name = "id")
	private int m_id;
	
	@Column(name = "name")
	private String m_name;
	
	public int getCatId() {
		return this.m_id;
	}
	
	public String getName(){
		return m_name;
	}
	
	public void setName(String p_name) {
		this.m_name = p_name;
	}
	
	public void setId(int p_id) {
		this.m_id = p_id;
	}
}
