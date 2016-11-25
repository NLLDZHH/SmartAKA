package com.njust.SmartAKA.service.impl;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.omg.CORBA.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.njust.SmartAKA.dao.UserDao;
import com.njust.SmartAKA.model.User;
import com.njust.SmartAKA.service.UserService;

@Service("newsService")
public class UserServiceImp implements UserService {
	@Autowired
    private UserDao userDao;
	
	private static ArrayList<HashMap> userList = new ArrayList();
	
	private static int USER_NUM = 2;
	
	private void clearList()
	{
		userList.clear();
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<HashMap> getAllUsers() 
	{
		if (userList.size() <= USER_NUM || userList.isEmpty())
		{
			clearList();
			
			for(User tmp:userDao.selectAllUsers())
			{
				HashMap<String, String> Map = new HashMap<String, String>();
			//	Map.put("id", tmp.);
				Map.put("username", tmp.getUsername());
				//Map.put("", tmp.getContent());
				
				
				userList.add(Map);
				
				
			}
			
		}
	
		return userList;
	}
}
