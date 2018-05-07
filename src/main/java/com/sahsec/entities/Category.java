package com.sahsec.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Cascade;

@Entity
public class Category {

	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "id")
	private int m_id;
	
	@Column(name = "name")
	private String m_name;
	
	@OneToOne
    @JoinColumn(name = "category_id")
	private Category m_parent;
	
	@ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private User m_user;
	
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
	
	public Category getParent() {
		return this.m_parent;
	}
	
	public void setParent(Category p_parent) {
		this.m_parent = p_parent;
	}
	
	public User getUser() {
		return this.m_user;
	}
	
	public void setUser(User p_user) {
		this.m_user = p_user;
	}
	
}
