package com.sahsec.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Payment {
	
	public enum PaymentType{
		INCOMING, OUTGOING
	}

	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "id")
	private int m_id;
	
	@Column(name = "name")
	private String m_name;
	
	@Column(name = "type")
	private PaymentType m_type;

	@Column(name = "amount")
	private int m_amount;
	
	@OneToOne
    @JoinColumn(name = "category_id")
	private Category m_category;
	
	@ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private User m_user;
	
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

	public PaymentType getType() {
		return m_type;
	}

	public void setType(PaymentType p_type) {
		this.m_type = p_type;
	}

	public Category getCategory() {
		return m_category;
	}

	public void setCategory(Category p_category) {
		this.m_category = p_category;
	}

	public User getUser() {
		return m_user;
	}

	public void setUser(User p_user) {
		this.m_user = p_user;
	}
	
	public void setAmount(int p_amount) {
		this.m_amount = p_amount;
	}
	
	public int getAmount() {
		return this.m_amount;
	}
	
}
