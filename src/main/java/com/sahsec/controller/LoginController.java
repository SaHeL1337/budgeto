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
import com.sahsec.entities.User;

import service.Patch;
import service.PatchHistory;
 
@Controller
public class LoginController {
	
	PatchHistory patchHistory;
	Session dbSession;
	
	@RequestMapping("/")
	public ModelAndView loginPage() {
		
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
		
		
		return new ModelAndView("login", "message", "");
	}
	
	
	@RequestMapping("/applyPatch/{id}")
	public ModelAndView applyPatch(@PathVariable("id") int id) {
		Patch l_patch = patchHistory.getPatchById(id);
		if(l_patch.getStatus()==Patch.PatchStatus.READY) {
		}
		return new ModelAndView("redirect:/upload", "patchHistory", patchHistory);
	}
	
	@RequestMapping(value = "/uploadPatch", method = RequestMethod.POST)
	public ModelAndView submit(@RequestParam("file") MultipartFile file,@RequestParam("patchName") String patchName, ModelMap modelMap) {
	    Patch p_patch = new Patch(patchName, file, patchHistory.getHistory().size());
	    
	    patchHistory.addPatchToHistory(p_patch);
	    
	    return new ModelAndView("redirect:/upload", "patchHistory", patchHistory);
	}
	
	@RequestMapping(value="/login", method = RequestMethod.POST)
	public ModelAndView test2(String username, String password) {
		String message ="";

		if(username == null || !username.equals("admin")) {
			message = "Incorrect username or password";
			return new ModelAndView("login", "message", message);
		}else {
			message = "Thank you for loggin in @" + username + password;
			return new ModelAndView("upload", "message", message);
		}
		
	}
	
	@PostConstruct
	public void init() {
	   //init hibernate and tables
	   Configuration cfg = new Configuration();
	   SessionFactory sessionFactory = cfg.configure("hibernate.cfg.xml").buildSessionFactory();
	   dbSession = sessionFactory.openSession();
	}
	
	private static Connection getRemoteConnection() throws ClassNotFoundException, SQLException { 
      Class.forName("org.postgresql.Driver");
      String dbName = "Budgeto";
      String userName = "postgres";
      String password = "root";
      String hostname = "localhost";
      String port = "5432";
      String jdbcUrl = "jdbc:postgresql://" + hostname + ":" + port + "/" + dbName + "?user=" + userName + "&password=" + password;
      Connection con = DriverManager.getConnection(jdbcUrl);
      return con;
	}
	
}