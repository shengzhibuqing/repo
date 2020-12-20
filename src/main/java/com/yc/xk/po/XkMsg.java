package com.yc.xk.po;

import java.sql.Date;
import java.sql.Timestamp;

public class XkMsg {
    private Integer id;

    private String content;

    private String reply;

    private String createName;

    private Timestamp createTime;
    
    private String Email;
    
    private Date replyTime;

	public Date getReplyTime() {
		return replyTime;
	}

	public void setReplyTime(Date replyTime) {
		this.replyTime = replyTime;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply == null ? null : reply.trim();
    }

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName == null ? null : createName.trim();
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void Timestamp(Timestamp createTime) {
        this.createTime = createTime;
    }
}