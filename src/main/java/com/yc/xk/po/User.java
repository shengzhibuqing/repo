package com.yc.xk.po;

import java.sql.Timestamp;

public class User {
	
	private Integer uid;

    private String phone;

    private String email;
    
    private String name;
    
    private String head;
    
    private String pwd;
    
    

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getHead() {
		return head;
	}

	public void setHead(String head) {
		this.head = head;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}


	@Override
	public String toString() {
		return "User [uid=" + uid + ", phone=" + phone + ", email=" + email + ", name=" + name + ", head=" + head
				+ ", pwd=" + pwd + "]";
	}
    
    

}
