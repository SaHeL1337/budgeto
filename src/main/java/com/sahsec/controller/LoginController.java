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
import com.sahsec.entities.User;

import service.Patch;
import service.PatchHistory;
 
@Controller
public class LoginController {
	
	PatchHistory patchHistory;
	Session dbSession;
 
	@Bean(name = "multipartResolver")
	public CommonsMultipartResolver multipartResolver() {
	    CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
	    multipartResolver.setMaxUploadSize(100000);
	    return multipartResolver;
	}
	
	@RequestMapping("/")
	public ModelAndView loginPage() {
		
		dbSession.beginTransaction();
		
		User user = new User();
		user.setName("Herbert");
		
		dbSession.save(user);
		dbSession.getTransaction().commit();
		
		
		return new ModelAndView("login", "message", "");
	}
	
	@RequestMapping("/upload")
	public ModelAndView uploadPage() {
		
		return new ModelAndView("upload", "patchHistory", patchHistory);
	}
	
	@RequestMapping("/applyPatch/{id}")
	public ModelAndView applyPatch(@PathVariable("id") int id) {
		Patch l_patch = patchHistory.getPatchById(id);
		if(l_patch.getStatus()==Patch.PatchStatus.READY) {
			l_patch.applyPatch();
		}
		return new ModelAndView("redirect:/upload", "patchHistory", patchHistory);
	}
	
	@RequestMapping("/rollbackPatch/{id}")
	public ModelAndView rollbackPatch(@PathVariable("id") int id) {
		Patch l_patch = patchHistory.getPatchById(id);
		if(l_patch.getStatus()==Patch.PatchStatus.PATCHED) {
			l_patch.rollback();
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