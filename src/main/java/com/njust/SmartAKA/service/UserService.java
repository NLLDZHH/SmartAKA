package com.njust.SmartAKA.service;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.njust.SmartAKA.model.User;


public interface UserService{
	
	public List<HashMap> getAllUsers();
	
}