package com.njust.SmartAKA.model;
import java.io.Serializable;
import java.sql.Date;
import java.util.Set;
/**
 * 用户实体
 * @author fuxin
 *
 */
public class Users implements Serializable{
	// 关系
	
	//属性
	private Integer id;
	private String username;//用户名
	private String password;
	
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
    public String toString() {
        return "user [username=" + username +  "]";
    }
}