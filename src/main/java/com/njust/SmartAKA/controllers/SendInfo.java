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
public class SendInfo {
   
	
	/**
	private String userName0,userName1;
	@RequestMapping(value = "users/keys/sendto{username}" ,method = {RequestMethod.POST,RequestMethod.GET})
    public  void sendInfo (@PathVariable("username") String username) { 
	    userName0=username.split("#")[0];
	    userName1=username.split("#")[1];
	    System.out.println(userName1);
	  
	
	}
	
	@RequestMapping(value = "users/keys/receive{username}" ,method = {RequestMethod.GET})
    public  @ResponseBody List<HashMap> receiveInfo (@PathVariable("username") String username) { 
	   System.out.println("看看到底是多少"+username);
		username=userName1;
	    List<HashMap> listone = new ArrayList();
		   HashMap<String, String> newsMap = new HashMap<String, String>();
			newsMap.put("P",userName0);
			//newsMap.put("content", tmp.getTitle());
		   listone.add(newsMap);
		    return listone;
	
	}
	**/
	
}
