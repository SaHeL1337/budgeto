package com.sahsec.service.implementation;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DatabaseService {
	
	//TODO think of nice concept for this database thing
	private static Session SESSION;

	private static Session init() {
	   Configuration cfg = new Configuration();
	   SessionFactory sessionFactory = cfg.configure("hibernate.cfg.xml").buildSessionFactory();
	   SESSION = sessionFactory.openSession();
	   return SESSION;
	}
	
	public static Session getSession() {
		if(SESSION == null) {
			return init();
		}else {
			return SESSION;
		}
		
	}
}
