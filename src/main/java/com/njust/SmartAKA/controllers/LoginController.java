package com.njust.SmartAKA.controllers;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


import com.njust.SmartAKA.dao.UserDao;

import com.njust.SmartAKA.model.User;
import com.njust.SmartAKA.model.Users;
import com.njust.SmartAKA.controllers.*;

@Controller
public class LoginController {
		
	@Autowired
	private UserDao ecuserDao;
	
	
	@Autowired
	private UserDao ecuserDaoOne;//用于前向安全性页面loginone.html登录
	
	@Autowired
	private UserDao ecuserDaoTwo;//用于密钥妥协假冒页面logintwo.html登录
	
	@Autowired
	private UserDao ecuserDaoThree;//用于未知密钥共享页面loginthree.html登录

	
	
	
	
	
	
	
	@RequestMapping("/logins/login")
    public String getCarPartialPage() {
		
	   
        return "logins/login";
       
    }
	
	
	@RequestMapping("/logins/loginone")
    public String getLoginOne() {
		
	   
        return "logins/loginone";
       
    }
	
	
	@RequestMapping("/logins/logintwo")
    public String getLoginTwo() {
		
	   
        return "logins/logintwo";
       
    }
	
	
	@RequestMapping("/logins/loginthree")
    public String getLoginThree() {
		
	   
        return "logins/loginthree";
       
    }
	
	
	
	
	
	
	@RequestMapping("/logins/login/Ecuser")
   public String login(HttpServletRequest request,HttpServletResponse response,User entity,HttpSession httpSession )
	{      
		  // System.out.println("输入的用户名："+request.getParameter("usernamee"));
		 //  System.out.println("输入的密码："+request.getParameter("passwd"));   
		   String username=request.getParameter("usernamee");
		   String password=request.getParameter("passwd");
		  // System.out.println(username.length());
		//   System.out.println(password.length());
	
	   
	if((2<username.length()&username.length()<13)&&(2<password.length()&password.length()<13)){   
		if(ecuserDao.findByUsername(request.getParameter("usernamee"))!=null){
			entity = ecuserDao.findByUsername(request.getParameter("usernamee"));
			if(entity.getPassword().compareTo(request.getParameter("passwd"))==0){
				System.out.println("登录成功");
				  return "redirect:/users/Name="+username; 
		        }
		     else{
					System.out.println("用户名与密码不匹配导致登录失败");
	                return "redirect:/logins/login";
				}
		}
		
		else{
			System.out.println("用户名拼写错误错误，或者该用户没有注册，导致登录失败"); 
		     return "redirect:/logins/login";
         }
		}
	else{
	     System.out.println("长短空不符合要求");
	     return "redirect:/logins/login";
		}
	}
	/**
	 *前向安全性页面跳转控制器
	 */
	
	
	@RequestMapping("/logins/loginone/Ecuser")
	   public String loginone(HttpServletRequest request,HttpServletResponse response,User entity,HttpSession httpSession )
		{      
			   System.out.println("输入的用户名："+request.getParameter("usernamee"));
			   System.out.println("输入的密码："+request.getParameter("passwd"));   
			   String username=request.getParameter("usernamee");
			   String password=request.getParameter("passwd");
			   System.out.println(username.length());
			   System.out.println(password.length());
			  
			   
	    
	 
		if((2<username.length()&username.length()<13)&&(2<password.length()&password.length()<13)){   
			if(ecuserDaoOne.findByUsernameFromaccountone(request.getParameter("usernamee"))!=null){
				entity = ecuserDaoOne.findByUsernameFromaccountone(request.getParameter("usernamee"));
				if(entity.getPassword().compareTo(request.getParameter("passwd"))==0){
					System.out.println("登录成功");
					  return "redirect:/forward/Name="+username; 
			        }
			     else{
						System.out.println("用户名与密码不匹配导致登录失败");
		                return "redirect:/logins/loginone";
					}
			}
			
			else{
				System.out.println("用户名拼写错误错误，或者该用户没有注册，导致登录失败"); 
			     return "redirect:/logins/loginone";
	         }
			}
		else{
		     System.out.println("长短空不符合要求");
		     return "redirect:/logins/loginone";
			}
		}
	
	/**
	 *密钥妥协假冒页面跳转控制器
	 */
	@RequestMapping("/logins/logintwo/Ecuser")
	   public String logintwo(HttpServletRequest request,HttpServletResponse response,User entity,HttpSession httpSession )
		{      
			  // System.out.println("输入的用户名："+request.getParameter("usernamee"));
			 //  System.out.println("输入的密码："+request.getParameter("passwd"));   
			   String username=request.getParameter("usernamee");
			   String password=request.getParameter("passwd");
			  // System.out.println(username.length());
			//   System.out.println(password.length());
		
		   
		if((2<username.length()&username.length()<13)&&(2<password.length()&password.length()<13)){   
			if(ecuserDaoTwo.findByUsernameFromaccountone(request.getParameter("usernamee"))!=null){
				entity = ecuserDaoTwo.findByUsernameFromaccountone(request.getParameter("usernamee"));
				if(entity.getPassword().compareTo(request.getParameter("passwd"))==0){
					System.out.println("登录成功");
					  return "redirect:/impersonate/Name="+username; 
			        }
			     else{
						System.out.println("用户名与密码不匹配导致登录失败");
		                return "redirect:/logins/logintwo";
					}
			}
			
			else{
				System.out.println("用户名拼写错误错误，或者该用户没有注册，导致登录失败"); 
			     return "redirect:/logins/logintwo";
	         }
			}
		else{
		     System.out.println("长短空不符合要求");
		     return "redirect:/logins/logintwo";
			}
		}
	/**
	 * 未知密钥共享页面跳转控制器
	 */
	@RequestMapping("/logins/loginthree/Ecuser")
	   public String loginthree(HttpServletRequest request,HttpServletResponse response,User entity,HttpSession httpSession )
		{      
			  // System.out.println("输入的用户名："+request.getParameter("usernamee"));
			 //  System.out.println("输入的密码："+request.getParameter("passwd"));   
			   String username=request.getParameter("usernamee");
			   String password=request.getParameter("passwd");
			  // System.out.println(username.length());
			//   System.out.println(password.length());
		
		   
		if((2<username.length()&username.length()<13)&&(2<password.length()&password.length()<13)){   
			if(ecuserDaoThree.findByUsernameFromaccountone(request.getParameter("usernamee"))!=null){
				entity = ecuserDaoThree.findByUsernameFromaccountone(request.getParameter("usernamee"));
				if(entity.getPassword().compareTo(request.getParameter("passwd"))==0){
					System.out.println("登录成功");
					  return "redirect:/unknew/Name="+username; 
			        }
			     else{
						System.out.println("用户名与密码不匹配导致登录失败");
		                return "redirect:/logins/loginthree";
					}
			}
			
			else{
				System.out.println("用户名拼写错误错误，或者该用户没有注册，导致登录失败"); 
			     return "redirect:/logins/loginthree";
	         }
			}
		else{
		     System.out.println("长短空不符合要求");
		     return "redirect:/logins/loginthree";
			}
		}
	
}	

