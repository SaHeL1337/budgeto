package com.sahsec.entities;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="UserObj")
public class User {
	

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int m_id;
	
	@Column(name = "name")
	private String m_name;
	
	@Column(name = "password")
	private String m_password;

	@Column(name = "email")
	private String m_email;
	
	@OneToMany(mappedBy="m_user", cascade = CascadeType.ALL)
	private Set<Payment> m_payments;
	
	@OneToMany(mappedBy="m_user", cascade = CascadeType.ALL)
	private Set<Category> m_categories;

	public int getId() {
		return m_id;
	}

	public void setId(int p_id) {
		this.m_id = p_id;
	}

	public String getName() {
		return m_name;
	}

	public void setName(String p_name) {
		this.m_name = p_name;
	}

	public String getPassword() {
		return m_password;
	}

	public void setPassword(String p_password) {
		this.m_password = p_password;
	}

	public String getEmail() {
		return m_email;
	}

	public void setEmail(String p_email) {
		this.m_email = p_email;
	}

	public Set<Payment> getPayments() {
		return m_payments;
	}

	public void setPayments(Set<Payment> p_payments) {
		this.m_payments = p_payments;
	}
	
	public Set<Category> getCategories() {
		return m_categories;
	}

	public void setCategories(Set<Category> p_categories) {
		this.m_categories = p_categories;
	}
}
