package com.njust.SmartAKA.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
  public class PageController{
	String nameOfUser;
	
	
	
	
	@RequestMapping(value = "/users/Name="+"{username}" ,method = {RequestMethod.GET, RequestMethod.POST})
    public String getUrlParam(@PathVariable("username") String username) {  
		    nameOfUser=username;	    
		    if(username.equals("admin")){
		    	return "users/admin";
		    }
		    else{
		        return "users/userslist"; 
		    }          
    }	
	@RequestMapping("users/news1/selectednews1.json")
	public @ResponseBody List<HashMap> userName(){
		System.out.println(nameOfUser);
		List<HashMap> listone = new ArrayList();
		   HashMap<String, String> newsMap = new HashMap<String, String>();
			newsMap.put("NAME",nameOfUser);
			//newsMap.put("content", tmp.getTitle());
		   listone.add(newsMap);
		    return listone;
	
	}
	
	/**
	 * 前向安全性登录页面跳转
	 */
	@RequestMapping(value = "/forward/Name="+"{username}" ,method = {RequestMethod.GET, RequestMethod.POST})
    public String getToForward(@PathVariable("username") String username) {  
		  		        return "forward/"+username; 
		          
    }	
	/**
	 * 密钥妥协假冒登录页面跳转
	 */
	@RequestMapping(value = "/impersonate/Name="+"{username}" ,method = {RequestMethod.GET, RequestMethod.POST})
    public String getToImpersonate(@PathVariable("username") String username) {  
		   
		           return "impersonate/"+username; 
		           
    }	
	/**
	 * 未知密钥共享登录页面跳转
	 */
	@RequestMapping(value = "/unknew/Name="+"{username}" ,method = {RequestMethod.GET, RequestMethod.POST})
    public String getToUnknew(@PathVariable("username") String username) {  
		 
		        return "unknew/"+username;  
		             
    }	
}