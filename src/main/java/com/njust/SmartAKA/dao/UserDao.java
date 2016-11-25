package com.njust.SmartAKA.dao;
import java.util.ArrayList;

import com.njust.SmartAKA.model.User;
//import com.app.model.News;

public interface UserDao 
{
	 /**
     * @param user
     * @return
     */
	 public User findByUsername(String username); 
	 
	 
	 public User findByUsernameFromaccountone(String username);
	 
	 
	 public ArrayList<User> selectAllUsers();
    
}
