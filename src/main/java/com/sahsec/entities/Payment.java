package com.sahsec.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Payment {
	
	public enum PaymentType{
		INCOMING, OUTGOING
	}

	@Id
	@Column(name = "id")
	private int m_id;
	
	@Column(name = "name")
	private String m_name;
	
	@Column(name = "type")
	private PaymentType m_type;
	
	@ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private User user;
	
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

	public PaymentType getType() {
		return m_type;
	}

	public void setType(PaymentType type) {
		this.m_type = type;
	}
	
	
}
