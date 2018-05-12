package com.sahsec.controller;


 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import javax.annotation.PostConstruct;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.json.simple.JSONObject;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;

import com.sahsec.entities.Category;
import com.sahsec.entities.Payment;
import com.sahsec.entities.Payment.PaymentType;
import com.sahsec.service.implementation.DatabaseService;
import com.sahsec.entities.User;
 
@Controller
public class LoginController {
	
	Session dbSession;
	
	@RequestMapping("/login")
	public ModelAndView loginPage() {
		/*
		dbSession.beginTransaction();
		
		User user = new User();
		user.setName("Herbert");
		user.setEmail("no@test.real");
		user.setPassword("password");
		
		Category rootCat = new Category();
		rootCat.setName("root");
		rootCat.setUser(user);
		
		Category secCat = new Category();
		secCat.setName("secondCategory");
		secCat.setParent(rootCat);
		secCat.setUser(user);
		
		Payment incPayment = new Payment();
		incPayment.setCategory(rootCat);
		incPayment.setName("Gehalt");
		incPayment.setType(PaymentType.INCOMING);
		incPayment.setUser(user);
		
		Payment outPayment = new Payment();
		outPayment.setCategory(secCat);
		outPayment.setName("Schuhe");
		outPayment.setType(PaymentType.OUTGOING);
		outPayment.setUser(user);
		
		dbSession.saveOrUpdate(user);
		dbSession.saveOrUpdate(rootCat);
		dbSession.saveOrUpdate(secCat);
		dbSession.saveOrUpdate(incPayment);
		dbSession.saveOrUpdate(outPayment);
		dbSession.getTransaction().commit();
		
		*/
		return new ModelAndView("login", "message", "");
	}
	
	
	@RequestMapping("/")
	public ModelAndView home() {
		return new ModelAndView("upload", "message", "");
	}
	
	@PostConstruct
	public void init() {
	   dbSession = DatabaseService.getSession();
	}
	
}