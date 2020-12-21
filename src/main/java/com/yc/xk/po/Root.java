package com.yc.xk.po;

public class Root {
	
	private Integer iid;

    private String iname;
    
    private String password;	
    
    private String phone;
    
    private String email;

	public Integer getIid() {
		return iid;
	}

	public void setIid(Integer iid) {
		this.iid = iid;
	}

	public String getIname() {
		return iname;
	}

	public void setIname(String iname) {
		this.iname = iname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	@Override
	public String toString() {
		return "Root [iid=" + iid + ", iname=" + iname + ", password=" + password + ", phone=" + phone + ", email="
				+ email + "]";
	}	
    
    

}
