package com.sahsec.service;

import java.awt.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Service;

import com.sahsec.entities.Category;
import com.sahsec.entities.Payment;
import com.sahsec.entities.Payment.PaymentType;
import com.sahsec.entities.User;

@Service("userService")
@Transactional
public class UserService {
	
	public static User findBySso(String ssoId) {
		
		Session db = DatabaseService.getSession();
		CriteriaBuilder builder = db.getCriteriaBuilder();
		CriteriaQuery<User> criteria = builder.createQuery(User.class);
		Root<User> root = criteria.from(User.class);
		criteria.where(builder.equal(root.get("m_name"), ssoId));
		Query<User> query = db.createQuery(criteria);
		
		java.util.List<User> results = query.getResultList();
		if(!results.isEmpty() && results.size() == 1) {
			return query.getSingleResult();
		}
		
		return null;
	}

}
