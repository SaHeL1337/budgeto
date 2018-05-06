package com.sahsec.entities;

import java.util.Set;

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
	
	@OneToMany(mappedBy="user")
	private Set<Payment> payments;

	public int getId() {
		return m_id;
	}

	public void setId(int m_id) {
		this.m_id = m_id;
	}

	public String getName() {
		return m_name;
	}

	public void setName(String m_name) {
		this.m_name = m_name;
	}

	public String getPassword() {
		return m_password;
	}

	public void setPassword(String m_password) {
		this.m_password = m_password;
	}

	public String getEmail() {
		return m_email;
	}

	public void setEmail(String m_email) {
		this.m_email = m_email;
	}

	public Set<Payment> getPayments() {
		return payments;
	}

	public void setPayments(Set<Payment> payments) {
		this.payments = payments;
	}
}
