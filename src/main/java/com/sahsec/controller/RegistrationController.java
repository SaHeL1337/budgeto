package com.sahsec.controller;


 
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
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
import javax.validation.Constraint;
import javax.validation.Valid;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;

import com.sahsec.DTO.UserDto;
import com.sahsec.entities.Category;
import com.sahsec.entities.Payment;
import com.sahsec.entities.Payment.PaymentType;
import com.sahsec.service.implementation.DatabaseService;
import com.sahsec.service.implementation.UserService;
import com.sahsec.validator.implementation.EmailValidator;
import com.sahsec.entities.User;
import com.sahsec.exception.EmailExistsException;
import com.sahsec.exception.UserAlreadyExistException;
 
@Controller
public class RegistrationController {
	
	Session dbSession;
	
	UserService service;
	
	@RequestMapping("/register")
	public ModelAndView registrationPage() {
		
		return new ModelAndView("registration", "message", "");
	}
	
	@RequestMapping(value = "/user/registration", method = RequestMethod.GET)
	public String showRegistrationForm(WebRequest request, Model model) {
	    UserDto userDto = new UserDto();
	    model.addAttribute("user", userDto);
	    return "registration";
	}
	
	@RequestMapping(value = "/user/registration", method = RequestMethod.POST)
	public ModelAndView registerUserAccount(
	  @ModelAttribute("user") @Valid UserDto accountDto, 
	  BindingResult result, 
	  WebRequest request, 
	  Errors errors) {
	     
	    User registered = new User();
	    if (!result.hasErrors()) {
	        registered = createUserAccount(accountDto, result);
	    }
	    if (registered == null) {
	        result.rejectValue("email", "message.regError");
	    }
	    if (result.hasErrors()) {
	        return new ModelAndView("registration", "user", accountDto);
	    } 
	    else {
	        return new ModelAndView("successRegister", "user", accountDto);
	    }
	}
	private User createUserAccount(UserDto accountDto, BindingResult result) {
	    User registered = null;
	    try {
	        registered = service.registerNewUserAccount(accountDto);
	    } catch (UserAlreadyExistException e) {
	        return null;
	    }
	    return registered;
	}
	
	@PostConstruct
	public void init() {
	   dbSession = DatabaseService.getSession();
	   service = new UserService();
	}
	
}