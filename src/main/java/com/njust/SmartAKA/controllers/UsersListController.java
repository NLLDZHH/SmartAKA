package com.njust.SmartAKA.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;  

import javax.management.MBeanServer;
import javax.servlet.http.HttpServletRequest;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


import com.njust.SmartAKA.service.UserService;
//导入JPBC相应的jar包
//import it.unisa.dia.gas.jpbc.*;  
//import it.unisa.dia.gas.plaf.jpbc.pairing.PairingFactory;  
//import java.lang.reflect.Proxy;  
//import java.util.Calendar;  
//import java.text.SimpleDateFormat;  
//import java.util.Date;
@Controller
public class UsersListController{
	
	@Autowired
	private UserService userService;
	@RequestMapping("users/news/selectednews.json")
	public @ResponseBody List<HashMap> extractSecretKeyOfBob() {  
		  /**
        List<HashMap> listone = new ArrayList();
 	   HashMap<String, String> newsMap1 = new HashMap<String, String>();
 	  HashMap<String, String> newsMap2 = new HashMap<String, String>();
 		newsMap1.put("P","Alice" );
 		newsMap2.put("P", "Bob");
 		//newsMap.put("content", tmp.getTitle());
 	   listone.add(newsMap1);
 	  listone.add(newsMap2);
 	  return listone;
 	  **/
		if(userService.getAllUsers()!=null){
			System.out.println(userService.getAllUsers().size());
		}
		
 	    return userService.getAllUsers();
 	   
    }  
	
	
}

